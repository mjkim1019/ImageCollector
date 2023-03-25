package com.kakaobank.imagecollector.data

import com.kakaobank.imagecollector.data.model.ImageSearchResponse
import com.kakaobank.imagecollector.data.model.VideoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v2/search/image")
    suspend fun searchImage(
       @Query("query") query: String, // 검색 질의어
       @Query("sort") sort: String?, // accuracy OR recency, default=accuracy
       @Query("page") page: Int?, // 결과 페이지 번호, [1-50] default=1
       @Query("size") size: Int?, // 한 페이지에 보여질 문서 수, [1-80], default=80
    ): ImageSearchResponse

    @GET("/v2/search/vclip")
    suspend fun searchVideo(
        @Query("query") query: String, // 검색 질의어
        @Query("sort") sort: String?, // accuracy OR recency, default=accuracy
        @Query("page") page: Int?, // 결과 페이지 번호, [1-15]
        @Query("size") size: Int?, // 한 페이지에 보여질 문서 수, [1-30], default=15
    ): VideoSearchResponse

}