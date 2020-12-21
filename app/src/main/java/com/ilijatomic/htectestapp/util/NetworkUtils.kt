package com.ilijatomic.htectestapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.ilijatomic.htectestapp.common.HTECApplication

object NetworkUtils {

    fun isOnline(): Boolean {
        val cm = HTECApplication.instance.appComponent.getContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            val activeNetworkInfo = cm.activeNetworkInfo
            activeNetworkInfo.isConnectedOrConnecting
        } else {
            val activeNetwork = cm.activeNetwork
            if (activeNetwork != null) {
                val networkCapabilities = cm.getNetworkCapabilities(activeNetwork)
                (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
            } else {
                false
            }
        }
    }
}