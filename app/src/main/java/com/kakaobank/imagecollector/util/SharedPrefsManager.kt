package com.kakaobank.imagecollector.util

import android.content.Context
import android.content.SharedPreferences
import com.kakaobank.imagecollector.BuildConfig
import com.kakaobank.imagecollector.models.Item
import com.kakaobank.imagecollector.util.ImageCollectorConst.PREFS_STORAGE

object SharedPrefsManager {
    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

}