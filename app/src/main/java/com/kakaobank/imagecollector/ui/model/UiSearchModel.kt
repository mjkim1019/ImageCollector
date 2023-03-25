package com.kakaobank.imagecollector.ui.model

import com.kakaobank.imagecollector.util.ImageCollectorConst.DEFAULT_QUERY


sealed class UiAction {
    data class Search(val query: String) : UiAction() // 사용자가 검색 중
    data class Scroll(val currentQuery: String) : UiAction() // 사용자가 스크롤 중
}

data class UiState(
    val query: String = DEFAULT_QUERY,
    val lastQueryScrolled: String = DEFAULT_QUERY,
    val hasNotScrolledForCurrentSearch: Boolean = false
)