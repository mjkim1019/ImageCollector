package com.kakaobank.imagecollector.data

import com.kakaobank.imagecollector.data.model.ImageSearchResponse
import com.kakaobank.imagecollector.data.model.VideoSearchResponse
import com.kakaobank.imagecollector.models.ApiResult
import com.kakaobank.imagecollector.models.safeFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
): RemoteDataSource {
    override suspend fun searchImages(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): Flow<ApiResult<ImageSearchResponse>> =
        safeFlow {
            apiService.searchImage(query, sort, page, size)
        }.flowOn(ioDispatcher)

    override suspend fun searchVideo(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): Flow<ApiResult<VideoSearchResponse>> =
        safeFlow {
            apiService.searchVideo(query, sort, page, size)
        }.flowOn(ioDispatcher)

}