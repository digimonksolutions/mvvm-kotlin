package com.cryptocapital.data.remote.api

import android.accounts.NetworkErrorException
import android.content.Context
import com.cryptocapital.R
import com.cryptocapital.data.remote.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLException

object HandleException {

    fun <T> getMessage(e: Exception, context: Context): Response<T> {
        when (e) {
            is SocketTimeoutException -> {
                return Response.error(e.message.toString(), 10)
            }
            is SSLException -> {
                return Response.error(e.message.toString(),10)
            }
            is UnknownHostException -> {
                return Response.error(context.getString(R.string.alert_no_internet), 1020)
            }
            is ConnectException -> {
                return Response.error(context.getString(R.string.alert_no_internet), 0)
            }
            is TimeoutException -> {
                return Response.error(context.getString(R.string.alert_connection_time_out), 11)
            }
            is ParseException -> {
                return Response.error(context.getString(R.string.alert_parsing_error), 12)
            }
            is NetworkErrorException -> {
                return Response.error(context.getString(R.string.alert_no_internet), 0)
            }
            else -> {
                return Response.error(e.message.toString(), 101)
            }
        }
    }
}