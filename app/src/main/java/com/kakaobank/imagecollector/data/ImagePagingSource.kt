package com.kakaobank.imagecollector.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kakaobank.imagecollector.model.Item
import com.kakaobank.imagecollector.util.DateFormatter
import com.kakaobank.imagecollector.util.ImageCollectorConst
import com.kakaobank.imagecollector.util.ImageCollectorConst.DEBUG_DATA
import com.kakaobank.imagecollector.util.ImageCollectorConst.NETWORK_PAGE_SIZE
import okio.IOException
import retrofit2.HttpException

class ImagePagingSource(
    private val apiService: ApiService,
    private val query: String,
    private val storageList: LinkedHashMap<String, Item>
) : PagingSource<Int, Item>() {

    private val STARTING_PAGE_INDEX = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val position = params.key ?: STARTING_PAGE_INDEX
        var index = 0;
        return try {
            val response = apiService.searchImage(
                query,
                ImageCollectorConst.RECENCY,
                position,
                params.loadSize
            )
            val items = response.documents.map {
                val localDateTime = DateFormatter.convertToLocalDateTime(it.datetime)
                if (storageList[it.thumbnailUrl] != null) {
                    Log.d(DEBUG_DATA, "load: isExisted!! \n${it.thumbnailUrl}")
                }
                Item(
                    id = ++index,
                    imgUrl = it.thumbnailUrl,
                    dateTime = localDateTime,
                    isFavorite = storageList[it.thumbnailUrl] != null
                )
            }
            val nextKey = if (response.meta.isEnd) null
            else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = items,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val item = state.closestItemToPosition(anchorPosition) ?: return null
        return item.id
    }

}