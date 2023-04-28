package com.cryptocapital.data.repository

import android.content.Context
import com.cryptocapital.data.remote.Response
import com.cryptocapital.data.remote.api.RestClient

class RemoteRepository( private val restClient: RestClient) {

    suspend fun <T> get(classDataObject: Class<T>, url: String): Response<T> {
        return restClient.get(classDataObject,url)
    }
}