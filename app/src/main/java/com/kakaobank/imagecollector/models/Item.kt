package com.kakaobank.imagecollector.models

data class Item(
    val thumbnail: String, // thumbnail url
    val date: String, // yyyy년 mm월 dd일
    val time: String, // HH:MM aa
    var isFavorite: Boolean = false // 보관함에 저장 여부
)
