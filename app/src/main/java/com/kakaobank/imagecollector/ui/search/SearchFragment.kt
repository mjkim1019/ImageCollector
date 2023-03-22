package com.kakaobank.imagecollector.ui.search

import androidx.navigation.fragment.findNavController
import com.kakaobank.imagecollector.R
import com.kakaobank.imagecollector.base.BaseFragment
import com.kakaobank.imagecollector.databinding.FragmentSearchBinding

class SearchFragment: BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    override fun createView(binding: FragmentSearchBinding) {
    }

    override fun viewCreated() {
        binding.btnStorage.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_searchFragment_to_storageFragment)
        }
    }


}