package com.kakaobank.imagecollector.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kakaobank.imagecollector.models.Item
import com.kakaobank.imagecollector.util.ImageCollectorConst.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
){
    fun getSearchResultStream(query: String): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                ImagePagingSource(apiService, query)
            }
        ).flow
    }
}