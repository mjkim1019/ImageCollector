package com.kakaobank.imagecollector.ui.viewmodels

import com.kakaobank.imagecollector.base.BaseViewModel
import com.kakaobank.imagecollector.models.Item
import com.kakaobank.imagecollector.util.SharedPrefsManager
import kotlinx.coroutines.flow.*

class StorageViewModel : BaseViewModel() {
    private var _favoriteList: MutableList<Item> = mutableListOf()
    val favoriteList get() = _favoriteList

    init {
        _favoriteList = SharedPrefsManager.getFavoriteList()
    }
}