package com.kakaobank.imagecollector.di

import com.kakaobank.imagecollector.data.RemoteDataSourceImpl
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
    fun provideRepository(remoteDataSourceImpl: RemoteDataSourceImpl): Repository {
        return Repository(remoteDataSourceImpl)
    }
}