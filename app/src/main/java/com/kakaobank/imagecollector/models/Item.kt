package com.kakaobank.imagecollector.models

import java.time.LocalDateTime

data class Item(
    val id: Int,
    val imgUrl: String, // image url
    val dateTime: LocalDateTime,
    var isFavorite: Boolean = false // 보관함에 저장 여부
)
