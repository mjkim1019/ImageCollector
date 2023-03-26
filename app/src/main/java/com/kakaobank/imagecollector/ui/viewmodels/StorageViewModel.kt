package com.kakaobank.imagecollector.ui.viewmodels

import com.kakaobank.imagecollector.base.BaseViewModel
import com.kakaobank.imagecollector.model.Item
import com.kakaobank.imagecollector.util.SharedPrefsManager

class StorageViewModel : BaseViewModel() {
    private var _favoriteList: List<Item> = listOf()
    val favoriteList get() = _favoriteList

    init {
        val prefsFavoriteList = SharedPrefsManager.getFavoriteList()
        _favoriteList = ArrayList(prefsFavoriteList.values).reversed()
    }
}