package com.kakaobank.imagecollector.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kakaobank.imagecollector.models.Item
import com.kakaobank.imagecollector.util.DateFormatter
import com.kakaobank.imagecollector.util.ImageCollectorConst
import com.kakaobank.imagecollector.util.ImageCollectorConst.NETWORK_PAGE_SIZE
import okio.IOException
import retrofit2.HttpException

class ImagePagingSource (
    private val apiService: ApiService,
    private val query: String
): PagingSource<Int, Item>() {

    private val STARTING_PAGE_INDEX = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try{
            val response = apiService.searchImage(query, ImageCollectorConst.RECENCY, position, params.loadSize)
            val items = response.documents.map {
                val localDateTime = DateFormatter.convertToLocalDateTime(it.datetime)
                Item(
                    id =0,
                    imgUrl = it.thumbnailUrl,
                    dateTime = localDateTime,
                )
            }
            val nextKey = if (response.meta.isEnd) null
            else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = items,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position -1,
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