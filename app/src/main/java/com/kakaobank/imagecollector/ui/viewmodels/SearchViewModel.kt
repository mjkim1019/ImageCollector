package com.kakaobank.imagecollector.ui.viewmodels

import com.kakaobank.imagecollector.base.BaseViewModel
import com.kakaobank.imagecollector.data.Repository
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

    fun onSearchWordChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        _searchWord.value = s.toString()
    }


}