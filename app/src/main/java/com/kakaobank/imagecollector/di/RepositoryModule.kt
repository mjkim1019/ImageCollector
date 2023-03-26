package com.kakaobank.imagecollector.di

import com.kakaobank.imagecollector.data.ApiService
import com.kakaobank.imagecollector.data.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideRepository(apiService: ApiService): Repository {
        return Repository(apiService)
    }
}