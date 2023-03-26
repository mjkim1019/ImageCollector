package com.kakaobank.imagecollector.util

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object DateFormatter {
    private const val itemDatePattern = "yyyy년 MM월 dd일"
    private const val itemTimePattern = "hh:mm a"

    fun convertToLocalDateTime(date: String): LocalDateTime {
        val parseDate: OffsetDateTime =
            OffsetDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)

        return parseDate.toLocalDateTime()
    }

    fun convertLocalDateTimeToItemDateTime(localDateTime: LocalDateTime): Array<String> {
        val dateFormatter = DateTimeFormatter.ofPattern(itemDatePattern)
        val timeFormatter = DateTimeFormatter.ofPattern(itemTimePattern)
        val date = dateFormatter.format(localDateTime)
        val time = timeFormatter.format(localDateTime)
        return arrayOf(date, time)
    }
}