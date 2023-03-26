package com.kakaobank.imagecollector.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.kakaobank.imagecollector.databinding.ItemItemBinding
import com.kakaobank.imagecollector.models.Item
import com.kakaobank.imagecollector.util.DateFormatter
import com.kakaobank.imagecollector.util.SharedPrefsManager

class ItemViewHolder(
    private val binding: ItemItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(image: Item) {
        val dateTimeArray = DateFormatter.convertLocalDateTimeToItemDateTime(image.dateTime)
        binding.apply {
            Glide.with(binding.root)
                .load(image.imgUrl)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(12)))
                .into(ivImage)
            date = dateTimeArray[0]
            time = dateTimeArray[1]
            isFavorite = image.isFavorite

            btnFavorite.setOnClickListener {
                image.isFavorite = !image.isFavorite
                if (image.isFavorite) SharedPrefsManager.addItemInFavoriteList(image)
            }
        }
    }

}