package com.kakaobank.imagecollector.ui.viewmodels

import android.util.Log
import android.view.View
import com.kakaobank.imagecollector.base.BaseViewModel
import com.kakaobank.imagecollector.data.Repository
import com.kakaobank.imagecollector.util.ImageCollectorConst.DEBUT_SEARCH_VIEWMODEL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
): BaseViewModel() {
    private val _searchWord: MutableStateFlow<String> = MutableStateFlow("")
    val searchWord: StateFlow<String> get() = _searchWord

    private var _isSearching: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> get() = _isSearching

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