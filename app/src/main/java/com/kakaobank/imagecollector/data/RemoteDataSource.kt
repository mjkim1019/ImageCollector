package com.kakaobank.imagecollector.data

import kotlinx.coroutines.flow.Flow
import com.kakaobank.imagecollector.models.ApiResult
import com.kakaobank.imagecollector.models.ImageSearchResponse
import com.kakaobank.imagecollector.models.VideoSearchResponse

interface RemoteDataSource {
    suspend fun searchImages(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): Flow<ApiResult<ImageSearchResponse>>

    suspend fun searchVideo(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): Flow<ApiResult<VideoSearchResponse>>

}