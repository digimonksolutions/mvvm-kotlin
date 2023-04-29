package com.mvvm.cryptocapital.data.remote.auth

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (request.header(RetrofitContents.PARAM_NO_AUTHENTICATION) == null) {
            request = request.newBuilder().build()
        }

        return chain.proceed(request)
    }
}
