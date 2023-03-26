package com.kakaobank.imagecollector.ui.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kakaobank.imagecollector.R
import com.kakaobank.imagecollector.base.BaseFragment
import com.kakaobank.imagecollector.databinding.FragmentStorageBinding
import com.kakaobank.imagecollector.models.Item
import com.kakaobank.imagecollector.ui.adapters.ItemListAdapter
import com.kakaobank.imagecollector.ui.model.EmptyState
import com.kakaobank.imagecollector.ui.model.ScreenType
import com.kakaobank.imagecollector.ui.viewmodels.StorageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StorageFragment : BaseFragment<FragmentStorageBinding>(R.layout.fragment_storage) {

    private val viewModel: StorageViewModel by viewModels()

    override fun createView(binding: FragmentStorageBinding) {
    }

    override fun viewCreated() {
        initView()
        bindAdapter(itemListFlow = viewModel.favoriteList)
    }

    private fun initView() {
        binding.layoutItemList.layoutEmpty.screenType = ScreenType.STORAGE_SCREEN
        binding.layoutItemList.isStorage = true
        binding.layoutItemList.layoutEmpty.emptyState = EmptyState.NO_RESULT
    }

    private fun bindAdapter(itemListFlow: StateFlow<MutableList<Item>>) {
        val itemListAdapter = ItemListAdapter()
        binding.layoutItemList.rvItemList.apply {
            adapter = itemListAdapter
            layoutManager = GridLayoutManager(this.context, 2)
        }
        bindList(adapter = itemListAdapter, itemListFlow = itemListFlow)
    }

    private fun bindList(adapter: ItemListAdapter, itemListFlow: StateFlow<MutableList<Item>>) {
        lifecycleScope.launchWhenCreated {
            itemListFlow.collectLatest {
                adapter.submitList(it)
            }
        }

        lifecycleScope.launch {
            if (adapter.itemCount != 0) {
                binding.layoutItemList.layoutEmpty.emptyState = EmptyState.NOT_EMPTY
            } else {
                binding.layoutItemList.layoutEmpty.emptyState = EmptyState.NO_RESULT
            }
        }
    }

}