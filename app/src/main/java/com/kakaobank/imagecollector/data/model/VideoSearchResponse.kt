package com.kakaobank.imagecollector.data.model

import com.squareup.moshi.Json

data class VideoSearchResponse(
    val meta: MetaData,
    val documents: MutableList<KakaoVideo>
)

data class KakaoVideo(
    val title: String, // 동영상 제목
    val url: String,    // 동영상 링크
    val datetime: String, // 동영상 등록일, [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
    @Json(name = "play_time") val playTime: Int, // 동영상 재생시간 (초 단위)
    val thumbnail: String, // 동영상 미리보기 url
    val author: String // 동영상 업로더
)
