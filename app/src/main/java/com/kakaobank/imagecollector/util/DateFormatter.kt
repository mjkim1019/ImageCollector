package com.kakaobank.imagecollector.util

import java.text.SimpleDateFormat

object DateFormatter {
    const val responseDatePattern = "YYYY-MM-DD'T'hh:mm:ss.SSSZ"
    const val itemDatePattern = "yyyy년 mm월 dd일"
    const val itemTimePattern = "HH:MM aa"

    val responseFormat = SimpleDateFormat(responseDatePattern)
    val itemDateFormat = SimpleDateFormat(itemDatePattern)
    val itemTimeFormat = SimpleDateFormat(itemTimePattern)

    fun convertToItemDateAndTime(date: String): Array<String> {
        val input = responseFormat.parse(date)
        val outputDate = itemDateFormat.format(input)
        val outputTime = itemTimeFormat.format(input)
        return arrayOf(outputDate, outputTime)
    }
}