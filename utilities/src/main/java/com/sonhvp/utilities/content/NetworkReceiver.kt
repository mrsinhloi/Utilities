@file:Suppress("unused")
package com.sonhvp.utilities.content

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sonhvp.utilities.network.haveConnection

private const val ACTION_CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE"

class NetworkReceiver(
        private val onConnected: (intent: Intent) -> Unit,
        private val onDisconnected: (intent: Intent) -> Unit
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null) {
            if (intent.action == ACTION_CONNECTIVITY_CHANGE) {
                if (context.haveConnection()) {
                    onConnected(intent)
                } else {
                    onDisconnected(intent)
                }
            }
        }
    }

}

fun AppCompatActivity.registerNetworkReceiver(networkReceiver: NetworkReceiver) {
    this.registerReceiver(networkReceiver, IntentFilter(ACTION_CONNECTIVITY_CHANGE))
}

fun AppCompatActivity.unregisterNetworkReceiver(networkReceiver: NetworkReceiver) {
    this.unregisterReceiver(networkReceiver)
}

fun Fragment.registerNetworkReceiver(networkReceiver: NetworkReceiver) {
    this.context?.registerReceiver(networkReceiver, IntentFilter(ACTION_CONNECTIVITY_CHANGE))
}

fun Fragment.unregisterNetworkReceiver(networkReceiver: NetworkReceiver) {
    this.context?.unregisterReceiver(networkReceiver)
}
