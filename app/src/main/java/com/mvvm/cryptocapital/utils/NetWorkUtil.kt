package com.mvvm.cryptocapital.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.SupplicantState
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSpecifier
import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import android.util.Log

object NetWorkUtil {

    private val TAG = NetWorkUtil::class.simpleName.toString()
    private  var connectivityManager: ConnectivityManager? = null
    private  var wifiManager: WifiManager? = null
    private var netWorkListener: NetWorkListener? = null

    @NetworkType.Value
    var networkType = NetworkType.UNKNOWN

    fun initialize(context: Context) {
        if (connectivityManager == null && wifiManager == null) {
            connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            wifiManager =
                context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        }
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            networkType = NetworkType.DEFAULT
            Log.e(TAG, "Network connected")
            netWorkListener?.onConnectionAvailable()
        }

        override fun onLost(network: Network) {
            networkType = NetworkType.DEFAULT
            Log.e(
                TAG, "Network disconnected"
            )
            netWorkListener?.onConnectionLost()
        }
    }

    private val wifiCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            networkType = NetworkType.SCANNED_WIFI
            connectivityManager?.bindProcessToNetwork(network)
            netWorkListener?.onConnectionAvailable()
        }

        override fun onLost(network: Network) {
            networkType = NetworkType.SCANNED_WIFI
            netWorkListener?.onConnectionLost()
        }
    }

    fun onNetWorkChange(netWorkListener: NetWorkListener) {
        NetWorkUtil.netWorkListener = netWorkListener
        try {
            connectivityManager?.unregisterNetworkCallback(networkCallback)
            connectivityManager?.registerDefaultNetworkCallback(networkCallback)
        }catch (e:Exception){
            connectivityManager?.registerDefaultNetworkCallback(networkCallback)

        }
    }

    fun connectToWifi(
        ssid: String?,
        password: String?
    ) {
        if (ssid != null && password != null) {
            if (!getCurrentSsid().equals(ssid)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val wifiNetworkSpecifier = WifiNetworkSpecifier.Builder()
                        .setSsid(ssid)
                        .setWpa2Passphrase(password)
                        .build()
                    val wifiNetworkSuggestion = WifiNetworkSuggestion.Builder()
                        .setSsid(ssid)
                        .setWpa2Passphrase(password)
                        .build()
                    val suggestions = mutableListOf(wifiNetworkSuggestion)
                    wifiManager?.addNetworkSuggestions(suggestions)

                    val networkRequest = NetworkRequest.Builder()
                        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                        // .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        .setNetworkSpecifier(wifiNetworkSpecifier)
                        .build()
                    connectivityManager?.requestNetwork(networkRequest, wifiCallback)
                } else {
                    val networkRequest = NetworkRequest.Builder()
                        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                        .build()
                    connectivityManager?.registerNetworkCallback(networkRequest, wifiCallback)
                    if (!isWifiEnabled()) {
                        wifiManager?.isWifiEnabled = true
                    }
                    val wifiConfiguration = WifiConfiguration()
                    wifiConfiguration.SSID = ssid
                    wifiConfiguration.preSharedKey = password
                    val wifiID = wifiManager?.addNetwork(wifiConfiguration)
                    wifiManager?.disconnect()
                    if (wifiID != null) {
                        wifiManager?.enableNetwork(wifiID, true)
                    }
                    wifiManager?.reconnect()
                }
            } else {
                networkType = NetworkType.SCANNED_WIFI
                netWorkListener?.onConnectionAvailable()
            }
        }
    }

    private fun isWifiEnabled(): Boolean {
        return wifiManager?.isWifiEnabled == true
    }

    fun getCurrentSsid(): String? {
        var ssid: String? = null
        val connectionInfo = wifiManager?.connectionInfo
        if (connectionInfo != null &&
            connectionInfo.supplicantState == SupplicantState.COMPLETED
        ) {
            ssid = connectionInfo.ssid.replace("\"", "")
        }
        return ssid
    }

    fun release() {
        try {
            connectivityManager?.unregisterNetworkCallback(networkCallback)
        } catch (e: Exception) {
            Log.i(TAG, "unregisterNetworkCallback ", e)
        }
        try {
            connectivityManager?.unregisterNetworkCallback(wifiCallback)
        } catch (e: Exception) {
            Log.i(TAG, "unregisterWifiCallback ", e)
        }
    }
}
