package com.kakaobank.imagecollector.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kakaobank.imagecollector.models.Item
import com.kakaobank.imagecollector.util.ImageCollectorConst.DEBUG_DATA
import com.kakaobank.imagecollector.util.ImageCollectorConst.NETWORK_PAGE_SIZE
import com.kakaobank.imagecollector.util.SharedPrefsManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.collections.LinkedHashMap

class Repository @Inject constructor(
    private val apiService: ApiService
) {
    fun getSearchResultStream(query: String): Flow<PagingData<Item>> {
        val storageList = SharedPrefsManager.getFavoriteList()
        Log.d(DEBUG_DATA, "getSearchResultStream: \n${storageList}")
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                ImagePagingSource(apiService, query, storageList)
            }
        ).flow
    }
}