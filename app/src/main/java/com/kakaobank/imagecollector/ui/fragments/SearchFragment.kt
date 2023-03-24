package com.kakaobank.imagecollector.ui.fragments

import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kakaobank.imagecollector.R
import com.kakaobank.imagecollector.ui.adapters.ItemAdapter
import com.kakaobank.imagecollector.base.BaseFragment
import com.kakaobank.imagecollector.databinding.FragmentSearchBinding
import com.kakaobank.imagecollector.ui.viewmodels.SearchViewModel
import com.kakaobank.imagecollector.util.ImageCollectorConst
import com.kakaobank.imagecollector.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModels()

    override fun createView(binding: FragmentSearchBinding) {
        binding.vm = viewModel
    }

    override fun viewCreated() {
        binding.clItemList.bringToFront()
        bindingVm()
        setAdapter()
        setListener()
    }

    private fun bindingVm() {

    }
    private fun setAdapter() {
        val itemAdapter = ItemAdapter {
            // todo click 했을 때 어떻게 할지
        }
        binding.rvItemList.apply {
            adapter = itemAdapter
            layoutManager = GridLayoutManager(this.context, 2)
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

        binding.etSearch.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                Log.d(ImageCollectorConst.DEBUG_SEARCH_FRAGMENT, "setListener: ${viewModel.searchWord.value}")
                v.clearFocus()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        binding.btnCancel.setOnClickListener {
            viewModel.setSearchWord("")
            binding.etSearch.clearFocus()
        }

        binding.etSearch.setOnFocusChangeListener { v, hasFocus ->
            Log.d(ImageCollectorConst.DEBUG_SEARCH_FRAGMENT, "hasFocus: ${hasFocus}")
            if (!hasFocus) {
                hideKeyboard(requireContext(), v)
                viewModel.setIsSearching(false)
            }
        }



    }

}