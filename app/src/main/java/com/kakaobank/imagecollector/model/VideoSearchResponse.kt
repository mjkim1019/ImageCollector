package com.kakaobank.imagecollector.model

data class VideoSearchResponse(
    val metaData: MetaData,
    val documents: MutableList<KakaoVideo>
)

data class KakaoVideo(
    val title: String, // 동영상 제목
    val url: String,    // 동영상 링크
    val dateTime: String, // 동영상 등록일, [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
    val playTime: Int, // 동영상 재생시간 (초 단위)
    val thumbnail: String, // 동영상 미리보기 url
    val author: String // 동영상 업로더
)
