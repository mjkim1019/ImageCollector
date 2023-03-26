package com.kakaobank.imagecollector.util

import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import java.time.LocalDateTime

val localDateTimeDeserializer =
    JsonDeserializer<LocalDateTime> { json, typeOfT, context ->
        LocalDateTime.parse(
            json?.asString,
            DateFormatter.defaultFormatter
        )
    }
val localDateTimeSerializer =
    JsonSerializer<LocalDateTime> { src, typeOfSrc, context ->
        JsonPrimitive(
            DateFormatter.defaultFormatter.format(
                src
            )
        )
    }