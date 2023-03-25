package com.kakaobank.imagecollector.di

import com.kakaobank.imagecollector.BuildConfig
import com.kakaobank.imagecollector.data.ApiService
import com.kakaobank.imagecollector.data.RemoteDataSourceImpl
import com.kakaobank.imagecollector.util.ImageCollectorConst
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val BASE_URL = "https://dapi.kakao.com"
    private val API_KEY = "d77fdda2c3d66fa578ffffb00f107bda"
    private val AUTH_HEADER = "KakaoAK ${API_KEY}"

    private val networkInterceptor: Interceptor = Interceptor { chain ->
        val request = chain.request()
        try {
            val newRequest = request.newBuilder()
                .addHeader(ImageCollectorConst.AUTH_HEADER_KEY, AUTH_HEADER)
                .build()
            chain.proceed(newRequest)
        } catch (e: Exception) {
            Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(ImageCollectorConst.NETWORK_ERROR)
                .body((e.message ?: "").toResponseBody(null))
                .message(e.message.toString())
                .build()
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpBuilder(): OkHttpClient.Builder {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .addNetworkInterceptor(httpLoggingInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
        } else {
            OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
        }
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClientBuilder: OkHttpClient.Builder): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSourceImpl {
        return RemoteDataSourceImpl(apiService, Dispatchers.IO)
    }
}