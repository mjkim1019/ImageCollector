package com.kakaobank.imagecollector.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.kakaobank.imagecollector.R
import com.kakaobank.imagecollector.databinding.ActivityImagecollectorBinding
import com.kakaobank.imagecollector.util.DateFormatter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageCollectorActivity: AppCompatActivity() {

    lateinit var binding: ActivityImagecollectorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_imagecollector)
        binding.lifecycleOwner = this

        setNavHost()

        DateFormatter.convertToItemDateAndTime("2017-06-21T15:59:30.000+09:00")

    }

    private fun setNavHost(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }
}