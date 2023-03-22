package com.kakaobank.imagecollector.model

import com.squareup.moshi.Json

data class MetaData(
    @Json(name = "total_count") val totalCount: Int,
    @Json (name = "pageable_count") val pageableCount: Int,
    @Json (name = "is_end") val isEnd: Boolean
)
