package com.kakaobank.imagecollector.ui.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.kakaobank.imagecollector.base.BaseViewModel
import com.kakaobank.imagecollector.data.Repository
import com.kakaobank.imagecollector.models.Item
import com.kakaobank.imagecollector.ui.model.UiAction
import com.kakaobank.imagecollector.ui.model.UiState
import com.kakaobank.imagecollector.util.ImageCollectorConst.DEBUT_SEARCH_VIEWMODEL
import com.kakaobank.imagecollector.util.ImageCollectorConst.LAST_QUERY_SCROLLED
import com.kakaobank.imagecollector.util.ImageCollectorConst.LAST_SEARCH_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
): BaseViewModel() {
    private val _searchWord: MutableStateFlow<String> = MutableStateFlow("")
    val searchWord: StateFlow<String> get() = _searchWord

    private var _isSearching: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> get() = _isSearching

    // todo paging
    var itemList: MutableStateFlow<List<Item>> = MutableStateFlow(listOf<Item>())

    /**
     * UI 상태를 대표하는 Stream
     */
    lateinit var state: StateFlow<UiState>
    lateinit var pagingDataFlow: Flow<PagingData<Item>>


    // process 중단시 복원하기 위해 저장
    override fun onCleared() {
        savedStateHandle[LAST_SEARCH_QUERY] = state.value.query
        savedStateHandle[LAST_QUERY_SCROLLED] = state.value.lastQueryScrolled
        super.onCleared()
    }

    fun onSearchWordChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        setSearchWord(s.toString())
        if (!_isSearching.value && searchWord.value != "") setIsSearching(true)
    }

    val onClickClearBtn = View.OnClickListener {
        setSearchWord("")
        Log.d(DEBUT_SEARCH_VIEWMODEL, "onClickClearBtn: ${searchWord.value}")
    }

    fun setSearchWord(str: String) {
        _searchWord.value = str
    }
    fun setIsSearching(value: Boolean) {
        _isSearching.value = value
    }

}