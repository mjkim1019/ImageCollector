package com.kakaobank.imagecollector.util

import java.text.SimpleDateFormat

object DateFormatter {
    private const val responseDatePattern = "yyyy-MM-DD'T'hh:mm:ss.SSSZ"
    private const val itemDatePattern = "yyyy년 MM월 dd일"
    private const val itemTimePattern = "hh:mm aa"

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