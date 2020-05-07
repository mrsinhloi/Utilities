package com.sonhvp.utilities.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

@SuppressLint("MissingPermission")
fun Context.haveConnection(): Boolean {
    connectivityManager().run {
        val activeNetwork: NetworkInfo? = activeNetworkInfo
        return activeNetwork?.isConnected == true
    }
}

fun Context.connectivityManager(): ConnectivityManager = (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)