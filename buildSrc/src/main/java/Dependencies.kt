object Versions {
    // AndroidX
    const val APP_COMPAT = "1.6.1"
    const val MATERIAL = "1.8.0"
    const val PAGING = "3.1.1"

    // KTX
    const val CORE = "1.9.0"

    // Compose
    const val NAV_VERSION = "2.5.3"

    // API Library
    const val RETROFIT = "2.9.0"
    const val OKHTTP = "4.10.0"
    const val MOSHI = "1.14.0"

    // Other Libraries
    const val GLIDE = "4.15.0"
    const val GSON = "2.10.1"

    // Hilt
    const val HILT = "2.44"

    // Test
    const val JUNIT = "4.13.2"

    // Android Test
    const val TEST_EXT_JUNIT = "1.1.5"
    const val ESPRESSO_CORE = "3.5.1"
}

object AndroidX {
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val PAGING = "androidx.paging:paging-runtime:${Versions.PAGING}"
}

object NAVIGATION {
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAV_VERSION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAV_VERSION}"
}

object Google {
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${Versions.HILT}"
}

object Libraries {
    const val RETROFIT                   = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_CONVERTER_MOSHI    = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
    const val OKHTTP                     = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:${Versions.MOSHI}"
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
    const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
}

object UnitTest {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
}

object AndroidTest {
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.TEST_EXT_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
}