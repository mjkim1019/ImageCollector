package com.kakaobank.imagecollector.data.model

import com.squareup.moshi.Json


data class ImageSearchResponse(
    val metaData: MetaData,
    val documents: MutableList<KakaoImage>
)

data class KakaoImage(
    val collection: String,
    @Json(name = "thumbnail_url") val thumbnailUrl: String, // 미리보기 이미지 url
    @Json(name = "image_url") val imageUrl: String, // 이미지 url
    val width: Int,
    val height: Int,
    @Json(name = "display_sitename") val displaySitename: String, // 출처
    @Json(name = "doc_url") val docUrl: String, // 문서 url
    val datetime: String // 문서 작성 시간, [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
)