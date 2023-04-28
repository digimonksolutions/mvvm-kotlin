package com.cryptocapital.data.remote.api

import android.content.Context
import com.cryptocapital.BuildConfig
import com.cryptocapital.data.remote.Response
import com.cryptocapital.data.remote.auth.AuthenticationInterceptor
import com.cryptocapital.data.remote.service.APIEndPoints
import com.cryptocapital.data.remote.settings.Setting.Companion.BASE_URL
import com.cryptocapital.data.remote.settings.Setting.Companion.HEADER
import com.cryptocapital.data.remote.settings.Setting.Companion.IS_HEADER_REQUIRED
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier

class RestClient(private val context:Context) {

    /*
    * Get
    * */
    var retrofitClient:Retrofit? = null

    suspend fun <T> get(clazz: Class<T> , url: String): Response<T> {
        try {
            if (IS_HEADER_REQUIRED) {
                val response = getInstance().httpGet(url, HEADER)
                if (response.isSuccessful) {

                    if (clazz == String::class.java) {
                        return Response.success(response.body() as T, response.code())
                    } else {
                        return Response.success(
                            Gson().fromJson(
                                response.body(),
                                clazz
                            ), response.code()
                        )
                    }
                } else {
                    return Response.error(response.message(), response.code())
                }
            }else {
                val response = getInstance().httpGet(url)
                if (response.isSuccessful) {

                    if (clazz == String::class.java) {
                        return Response.success(response.body() as T,response.code())
                    } else {
                        return  Response.success(
                            Gson().fromJson(
                                response.body(),
                                clazz
                            ) , response.code())
                    }
                }else{
                    return Response.error(response.message(),response.code())
                }
            }
        } catch (e: Exception) {
            return HandleException.getMessage(e,context)
        }
    }



    /*
    * Create Client
    * */


    fun getInstance(): APIEndPoints {
        if (retrofitClient == null) {
            retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(makeHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
            return retrofitClient!!.create(APIEndPoints::class.java)
        }
        return retrofitClient!!.create(APIEndPoints::class.java)
    }

    fun makeHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(logInterceptor())
            .hostnameVerifier(HostnameVerifier { hostname, session ->  true})
            .addInterceptor(AuthenticationInterceptor())
            .build()
    }

    private fun logInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

}