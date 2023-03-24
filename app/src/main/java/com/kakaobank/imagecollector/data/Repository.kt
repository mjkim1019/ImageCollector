package com.kakaobank.imagecollector.data

import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSourceImpl
){
    fun getSearchItems(){

    }
}