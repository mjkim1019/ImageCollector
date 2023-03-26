package com.kakaobank.imagecollector.ui.viewmodels

import com.kakaobank.imagecollector.base.BaseViewModel
import com.kakaobank.imagecollector.models.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StorageViewModel : BaseViewModel() {
    private var _favoriteList: MutableStateFlow<MutableList<Item>> =
        MutableStateFlow(mutableListOf())
    val favoriteList: StateFlow<MutableList<Item>> get() = _favoriteList

}