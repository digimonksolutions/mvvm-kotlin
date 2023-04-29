package com.mvvm.cryptocapital.data.repository

import android.content.Context
import com.mvvm.cryptocapital.data.remote.Response
import com.mvvm.cryptocapital.data.remote.api.RestClient

class RemoteRepository( private val restClient: RestClient) {

    suspend fun <T> get(classDataObject: Class<T>, url: String): Response<T> {
        return restClient.get(classDataObject,url)
    }
}