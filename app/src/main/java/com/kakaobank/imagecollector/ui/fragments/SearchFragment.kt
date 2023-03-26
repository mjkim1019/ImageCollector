package com.kakaobank.imagecollector.ui.fragments

import android.util.Log
import android.view.KeyEvent
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kakaobank.imagecollector.R
import com.kakaobank.imagecollector.ui.adapters.ItemPagingAdapter
import com.kakaobank.imagecollector.base.BaseFragment
import com.kakaobank.imagecollector.databinding.FragmentSearchBinding
import com.kakaobank.imagecollector.models.Item
import com.kakaobank.imagecollector.ui.model.EmptyState
import com.kakaobank.imagecollector.ui.model.ScreenType
import com.kakaobank.imagecollector.ui.model.UiAction
import com.kakaobank.imagecollector.ui.model.UiState
import com.kakaobank.imagecollector.ui.viewmodels.SearchViewModel
import com.kakaobank.imagecollector.util.ImageCollectorConst.DEBUG_SEARCH_FRAGMENT
import com.kakaobank.imagecollector.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModels()

    override fun createView(binding: FragmentSearchBinding) {
        binding.vm = viewModel
    }

    override fun viewCreated() {
        initView()
        bindState(
            uiState = viewModel.state,
            pagingData = viewModel.pagingDataFlow,
            uiActions = viewModel.accept
        )
        setListener()
    }

    private fun initView() {
        binding.layoutItemList.layoutEmpty.screenType = ScreenType.SEARCH_SCREEN
        binding.layoutItemList.layoutEmpty.root.bringToFront()
        binding.layoutItemList.layoutEmpty.emptyState = EmptyState.NOT_YET
        binding.layoutItemList.isStorage = false
    }

    private fun bindState(
        uiState: StateFlow<UiState>,
        pagingData: Flow<PagingData<Item>>,
        uiActions: (UiAction) -> Unit
    ) {
        val itemPagingAdapter = ItemPagingAdapter()
        binding.layoutItemList.rvItemList.apply {
            adapter = itemPagingAdapter
            layoutManager = GridLayoutManager(this.context, 2)
        }

        bindSearch(
            uiState = uiState,
            onQueryChanged = uiActions
        )
        bindList(
            adapter = itemPagingAdapter, uiState = uiState,
            pagingData = pagingData,
            onScrollChanged = uiActions
        )
    }

    private fun bindSearch(
        uiState: StateFlow<UiState>,
        onQueryChanged: (UiAction.Search) -> Unit
    ) {
        binding.etSearch.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                Log.d(DEBUG_SEARCH_FRAGMENT, "bindSearch: ${viewModel.searchWord.value}")
                v.clearFocus()
                updateSearchInput(onQueryChanged)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        lifecycleScope.launch {
            uiState
                .map { it.query }
                .distinctUntilChanged()
                .collect(binding.etSearch::setText)
            Log.d(DEBUG_SEARCH_FRAGMENT, "bindSearch: ${viewModel.state.value}")
        }
    }

    private fun updateSearchInput(onQueryChanged: (UiAction.Search) -> Unit) {
        if (viewModel.searchWord.value != "") {
            binding.layoutItemList.rvItemList.scrollToPosition(0)
            onQueryChanged(UiAction.Search(query = viewModel.searchWord.value))
        }
    }

    private fun bindList(
        adapter: ItemPagingAdapter,
        uiState: StateFlow<UiState>,
        pagingData: Flow<PagingData<Item>>,
        onScrollChanged: (UiAction.Scroll) -> Unit
    ) {
        binding.layoutItemList.rvItemList.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy != 0) onScrollChanged(UiAction.Scroll(currentQuery = viewModel.state.value.query))
            }
        })
        val notLoading = adapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.source.refresh is LoadState.NotLoading }
        val hasNotScrolledForCurrentSearch = uiState
            .map { it.hasNotScrolledForCurrentSearch }
            .distinctUntilChanged()
        val shouldScrollToTop = combine(
            notLoading, hasNotScrolledForCurrentSearch, Boolean::and
        ).distinctUntilChanged()

        lifecycleScope.launch {
            pagingData.collectLatest { adapter.submitData(it) }
        }
        lifecycleScope.launch {
            shouldScrollToTop.collectLatest {
                if (it) binding.layoutItemList.rvItemList.scrollToPosition(0)
            }
        }
        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                // 검색 결과가 없는지 아직 검색 전인지 보여주기 위해
                val isDone = loadState.refresh is LoadState.NotLoading
                if (isDone) {
                    if (adapter.itemCount == 0) {
                        binding.layoutItemList.layoutEmpty.emptyState = EmptyState.NO_RESULT
                    } else {
                        binding.layoutItemList.layoutEmpty.emptyState = EmptyState.NOT_EMPTY
                    }
                }
                // page 이전/이후 가져올 때 로딩 중 보여주기 위해
                val isPrepend = loadState.source.prepend is LoadState.Loading
                val isAppend = loadState.source.append is LoadState.Loading
                binding.layoutItemList.prependProgress.isVisible = isPrepend
                binding.layoutItemList.appendProgress.isVisible = isAppend

                Log.d(DEBUG_SEARCH_FRAGMENT, "bindList: ${loadState}")
            }
        }
    }

    private fun setListener() {
        binding.btnStorage.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_searchFragment_to_storageFragment)
        }

        binding.etSearch.setOnClickListener {
            viewModel.setIsSearching(true)
        }

        binding.etSearch.setOnFocusChangeListener { v, hasFocus ->
            Log.d(DEBUG_SEARCH_FRAGMENT, "hasFocus: ${hasFocus}")
            if (!hasFocus) {
                hideKeyboard(requireContext(), v)
                viewModel.setIsSearching(false)
            }
        }


    }

}