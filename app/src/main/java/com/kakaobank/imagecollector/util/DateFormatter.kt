package com.kakaobank.imagecollector.util

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object DateFormatter {
    private const val defaultLocalDateTimePattern = "yyyy-MM-dd'T'HH:mm"
    private const val nowLocalDateTimePattern = "$defaultLocalDateTimePattern:ss.SSS"
    private const val itemDatePattern = "yyyy년 MM월 dd일"
    private const val itemTimePattern = "hh:mm a"

    fun convertToLocalDateTime(date: String): LocalDateTime {
        val parseDate: OffsetDateTime =
            OffsetDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)

        return parseDate.toLocalDateTime()
    }

    fun convertToLocalDateTime(dateTime: LocalDateTime): LocalDateTime {
        // localtime -> 원하는 format string
        val formatter = DateTimeFormatter.ofPattern(defaultLocalDateTimePattern)
        val formattedDateTime = formatter.format(dateTime)

        // string -> localDateTime
        val outputDateTime = LocalDateTime.parse(formattedDateTime)

        return outputDateTime
    }

    fun convertLocalDateTimeToItemDateTime(localDateTime: LocalDateTime): Array<String> {
        val dateFormatter = DateTimeFormatter.ofPattern(itemDatePattern)
        val timeFormatter = DateTimeFormatter.ofPattern(itemTimePattern)
        val date = dateFormatter.format(localDateTime)
        val time = timeFormatter.format(localDateTime)
        return arrayOf(date, time)
    }

}