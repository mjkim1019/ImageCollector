package com.kakaobank.imagecollector.util

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object DateFormatter {
    fun convertToItemDateAndTime(date: String): Array<String> {
        val parseDate: OffsetDateTime = OffsetDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        val outputDate = "${parseDate.year}년 ${parseDate.month.value}월 ${parseDate.dayOfMonth}일"
        val outputTime = "${parseDate.hour}시 ${parseDate.minute}분"
        return arrayOf(outputDate, outputTime)
    }
}