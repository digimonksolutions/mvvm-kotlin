package com.mvvm.cryptocapital.data.remote.service

import retrofit2.Response
import retrofit2.http.*
import java.util.*

public interface APIEndPoints {
    /*
    * GET
    * */
    @GET
    suspend fun httpGet(@Url url: String): Response<String>

    @GET
    suspend fun httpGet(@Url url: String, @HeaderMap headers: HashMap<String, String>?): Response<String>

}