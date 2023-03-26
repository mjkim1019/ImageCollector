package com.kakaobank.imagecollector.ui.adapters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.kakaobank.imagecollector.databinding.ItemItemBinding
import com.kakaobank.imagecollector.models.Item
import com.kakaobank.imagecollector.util.DateFormatter
import com.kakaobank.imagecollector.util.ImageCollectorConst.DEBUG_ITEM_VIEWHOLDER
import com.kakaobank.imagecollector.util.SharedPrefsManager
import java.time.LocalDateTime

class ItemViewHolder(
    private val binding: ItemItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(image: Item) {
        val dateTimeArray = DateFormatter.convertLocalDateTimeToItemDateTime(image.dateTime)
        var _newIsFavorite = false

        binding.apply {
            Glide.with(binding.root)
                .load(image.imgUrl)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(12)))
                .into(ivImage)
            date = dateTimeArray[0]
            time = dateTimeArray[1]
            isFavorite = image.isFavorite
            _newIsFavorite = image.isFavorite

            btnFavorite.setOnClickListener {
                _newIsFavorite = !_newIsFavorite
                isFavorite = _newIsFavorite
                image.isFavorite = _newIsFavorite
                Log.d(DEBUG_ITEM_VIEWHOLDER, "btnFavorite clicked ${isFavorite}")
                if (image.isFavorite) {
                    val currentDateTime = DateFormatter.convertToLocalDateTime(LocalDateTime.now())
                    image.savedDateTime = currentDateTime
                    SharedPrefsManager.addItemInFavoriteList(image)
                }
                else {
                    image.savedDateTime = null
                    SharedPrefsManager.removeItemInFavoriteList(image)
                }
            }

        }
    }

}