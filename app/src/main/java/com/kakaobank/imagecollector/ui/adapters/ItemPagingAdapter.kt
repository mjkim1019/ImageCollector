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

class ItemPagingAdapter(private val onClick: () -> Unit) :
    PagingDataAdapter<Item, ItemPagingAdapter.ViewHolder>(
        ITEM_DIFF_CALLBACK
    ) {
    inner class ViewHolder(private val binding: ItemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Item) {
            binding.apply {
                Glide.with(binding.root)
                    .load(image.imgUrl)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(12)))
                    .into(ivImage)
                date = image.date
                time = image.time
                isFavorite = image.isFavorite
            }
        }

        init {
            binding.btnFavorite.setOnClickListener { onClick }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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