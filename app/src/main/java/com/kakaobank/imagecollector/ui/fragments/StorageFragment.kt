package com.kakaobank.imagecollector.ui.fragments

import com.kakaobank.imagecollector.R
import com.kakaobank.imagecollector.base.BaseFragment
import com.kakaobank.imagecollector.databinding.FragmentStorageBinding
import com.kakaobank.imagecollector.ui.model.EmptyState
import com.kakaobank.imagecollector.ui.model.ScreenType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StorageFragment: BaseFragment<FragmentStorageBinding>(R.layout.fragment_storage) {
    override fun createView(binding: FragmentStorageBinding) {
    }

    override fun viewCreated() {
        initView()
    }

    private fun initView(){
        binding.layoutItemList.layoutEmpty.screenType = ScreenType.STORAGE_SCREEN
        binding.layoutItemList.isStorage = true
        binding.layoutItemList.layoutEmpty.emptyState = EmptyState.NO_RESULT
    }
}