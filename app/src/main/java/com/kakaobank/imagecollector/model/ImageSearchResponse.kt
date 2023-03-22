package com.kakaobank.imagecollector.model


data class ImageSearchResponse (
    val metaData: MetaData,
    val documents: MutableList<KakaoImage>
        )

data class KakaoImage (
    val collection: String,
    val thumbnail_url: String, // 미리보기 이미지 url
    val image_url: String, // 이미지 url
    val width: Int,
    val height: Int,
    val display_sitename: String, // 출처
    val doc_url: String, // 문서 url
    val datetime: String // 문서 작성 시간, [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
        )