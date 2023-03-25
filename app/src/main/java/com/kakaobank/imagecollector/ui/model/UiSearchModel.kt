package com.kakaobank.imagecollector.ui.model


sealed class UiAction {
    data class Search(val query: String) : UiAction()
    data class Scroll(val currentQuery: String) : UiAction()
}

sealed class UiState(
    val query: String,
    val lastQueryScrolled: String,
    val hasNotScrolledForCurrentSearch: Boolean = false
)