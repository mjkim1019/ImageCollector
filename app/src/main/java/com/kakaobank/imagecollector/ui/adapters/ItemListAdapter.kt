package com.kakaobank.imagecollector.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kakaobank.imagecollector.databinding.ItemItemBinding
import com.kakaobank.imagecollector.models.Item

class ItemListAdapter : RecyclerView.Adapter<ItemViewHolder>() {
    private var itemList: MutableList<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size


    fun submitList(list: MutableList<Item>) {
        itemList = list
    }
}