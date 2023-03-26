package com.kakaobank.imagecollector.ui.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kakaobank.imagecollector.data.Repository
import com.kakaobank.imagecollector.model.Item
import com.kakaobank.imagecollector.ui.model.UiAction
import com.kakaobank.imagecollector.ui.model.UiState
import com.kakaobank.imagecollector.util.ImageCollectorConst.DEBUG_SEARCH_VIEWMODEL
import com.kakaobank.imagecollector.util.ImageCollectorConst.DEFAULT_QUERY
import com.kakaobank.imagecollector.util.ImageCollectorConst.LAST_QUERY_SCROLLED
import com.kakaobank.imagecollector.util.ImageCollectorConst.LAST_SEARCH_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _searchWord: MutableStateFlow<String> = MutableStateFlow("")
    val searchWord: StateFlow<String> get() = _searchWord

    private var _isSearching: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> get() = _isSearching

    /**
     * UI 상태를 대표하는 Stream
     */
    var state: StateFlow<UiState>
    var pagingDataFlow: Flow<PagingData<Item>>

    val accept: (UiAction) -> Unit

    init {
        val initialQuery: String = savedStateHandle[LAST_SEARCH_QUERY] ?: DEFAULT_QUERY
        val lastQueryScrolled: String = savedStateHandle[LAST_QUERY_SCROLLED] ?: DEFAULT_QUERY
        val actionStateFlow = MutableSharedFlow<UiAction>()
        val searches = actionStateFlow
            .filterIsInstance<UiAction.Search>()
            .distinctUntilChanged()
            .onStart { emit(UiAction.Search(query = initialQuery)) }
        val queriesScrolled = actionStateFlow
            .filterIsInstance<UiAction.Scroll>()
            .distinctUntilChanged()
            // This is shared to keep the flow "hot" while caching the last query scrolled,
            // otherwise each flatMapLatest invocation would lose the last query scrolled,
            .shareIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                replay = 1
            )
            .onStart { emit(UiAction.Scroll(currentQuery = lastQueryScrolled)) }

        // 새로운 검색어마다 Pager 새로 만들기
        pagingDataFlow = searches.flatMapLatest {
            searchItems(searchWord.value)
        }.cachedIn(viewModelScope)

        state = combine(
            searches,
            queriesScrolled,
            ::Pair
        ).map { (search, scroll) ->
            UiState(
                query = search.query,
                lastQueryScrolled = scroll.currentQuery,
                hasNotScrolledForCurrentSearch = search.query != scroll.currentQuery
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = UiState()
        )

        accept = {
            viewModelScope.launch { actionStateFlow.emit(it) }
        }
    }

    // process 중단 시 복원하기 위해 저장
    override fun onCleared() {
        savedStateHandle[LAST_SEARCH_QUERY] = state.value.query
        savedStateHandle[LAST_QUERY_SCROLLED] = state.value.lastQueryScrolled
        super.onCleared()
    }

    private fun searchItems(queryString: String): Flow<PagingData<Item>> =
        repository.getSearchResultStream(queryString)

    fun onSearchWordChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        setSearchWord(s.toString())
        if (!_isSearching.value && searchWord.value != "") setIsSearching(true)
    }

    val onClickClearBtn = View.OnClickListener {
        setSearchWord("")
        Log.d(DEBUG_SEARCH_VIEWMODEL, "onClickClearBtn: ${searchWord.value}")
    }

    fun setSearchWord(str: String) {
        _searchWord.value = str
    }

    fun setIsSearching(value: Boolean) {
        _isSearching.value = value
    }

}