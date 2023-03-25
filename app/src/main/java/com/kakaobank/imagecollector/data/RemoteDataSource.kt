package com.kakaobank.imagecollector.data

import com.kakaobank.imagecollector.data.model.ImageSearchResponse
import com.kakaobank.imagecollector.data.model.VideoSearchResponse
import kotlinx.coroutines.flow.Flow
import com.kakaobank.imagecollector.models.ApiResult

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