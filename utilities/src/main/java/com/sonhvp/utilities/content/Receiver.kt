package com.sonhvp.utilities.content

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class Receiver(private val onTrigger: ((context: Context, intent: Intent) -> Unit)) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null) onTrigger(context, intent)
    }
    
}