package com.kakaobank.imagecollector.fragments

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kakaobank.imagecollector.R
import com.kakaobank.imagecollector.adapters.ItemAdapter
import com.kakaobank.imagecollector.base.BaseFragment
import com.kakaobank.imagecollector.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    override fun createView(binding: FragmentSearchBinding) {
    }

    override fun viewCreated() {
        setAdapter()
        setListener()
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
    }

}