package com.kakaobank.imagecollector.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.kakaobank.imagecollector.databinding.ItemItemBinding
import com.kakaobank.imagecollector.models.Item
import com.kakaobank.imagecollector.util.DateFormatter.convertLocalDateTimeToItemDateTime

class ItemPagingAdapter(private val onClick: () -> Unit) :
    PagingDataAdapter<Item, ItemViewHolder>(ITEM_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onClick
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    companion object {
        private val ITEM_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem.imgUrl == newItem.imgUrl
        }
    }
}