package com.kakaobank.imagecollector.models

import java.time.LocalDateTime

data class Item(
    val id: Int,
    val imgUrl: String, // image url
    val dateTime: LocalDateTime, // yyyy-MM-dd'T'HH:mm
    var isFavorite: Boolean = false, // 보관함에 저장 여부
    var savedDateTime: LocalDateTime? = null // 보관함에 저장된 날짜 yyyy-MM-dd'T'HH:mm
)
