package com.kakaobank.imagecollector.util

object ImageCollectorConst {
    /**
     * SHARED PREFS MANAGER
     */
    const val PREFS_STORAGE = "favorite_storage"
    const val PREFS_DEFAULT_RESULT = "none"

    /**
     * NETWORK
     */
    const val AUTH_HEADER_KEY = "Authorization"
    const val NETWORK_ERROR = 1001

    /**
     * API
     */
    const val RECENCY = "recency" // 최신순
    const val NETWORK_PAGE_SIZE = 25

    /**
     * Search UI
     */
    const val LAST_SEARCH_QUERY = "last_search_query"
    const val LAST_QUERY_SCROLLED = "last_query_scrolled"
    const val DEFAULT_QUERY = "카카오뱅크"

    /**
     * DEBUG TAG
     */
    const val DEBUG_SEARCH_FRAGMENT = "DEBUG_SearchFragment"
    const val DEBUG_SEARCH_VIEWMODEL = "DEBUG_SearchViewModel"
    const val DEBUG_DATE_FORMATTER = "DEBUG_DateFormatter"
    const val DEBUG_ITEM_VIEWHOLDER = "DEBUG_ItemViewHolder"
    const val ERROR_PREFS = "ERROR_Prefs"
}