package com.kakaobank.imagecollector

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ImageCollectorApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}