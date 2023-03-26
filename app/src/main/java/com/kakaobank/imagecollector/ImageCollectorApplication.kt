package com.kakaobank.imagecollector

import android.app.Application
import com.kakaobank.imagecollector.util.SharedPrefsManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ImageCollectorApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPrefsManager.init(applicationContext)
    }
}