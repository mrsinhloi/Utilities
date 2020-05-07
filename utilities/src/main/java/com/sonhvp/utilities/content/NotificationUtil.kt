package com.sonhvp.utilities.content

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.sonhvp.utilities.R
import java.lang.Exception

fun notificationChannelAttr(block: NotificationChannelAttr.() -> Unit = {}): NotificationChannelAttr = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    NotificationChannelAttr().apply(block)
} else {
    throw Exception("notificationChannelAttr is null!")
}
fun Context.notificationChannel(block: NotificationChannelAttr.() -> Unit = {}) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        notificationService().run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannelAttr = NotificationChannelAttr().apply(block)
                createNotificationChannel(NotificationChannel(notificationChannelAttr.id, notificationChannelAttr.name, notificationChannelAttr.importance))
            }
        }
    }
}

fun notificationAttr(block: NotificationAttr.() -> Unit): NotificationAttr = NotificationAttr().apply(block)
fun Context.notification(channelId: String, notificationAttr: NotificationAttr): Notification = NotificationCompat.Builder(this, channelId).apply {
    notificationAttr.setAttrs().invoke(this)
}.build()

fun Context.notify(notificationChannelAttr: NotificationChannelAttr, notificationAttr: NotificationAttr) {
    notificationService().run {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(NotificationChannel(notificationChannelAttr.id, notificationChannelAttr.name, notificationChannelAttr.importance))
        }
    }
    val notification = NotificationCompat.Builder(this, notificationChannelAttr.id).apply {
        notificationAttr.setAttrs().invoke(this)
    }.build()

    NotificationManagerCompat.from(this).notify(notificationAttr.id, notification)
}

@RequiresApi(Build.VERSION_CODES.N)
data class NotificationChannelAttr(
    var id: String = DEFAULT,
    var name: String = DEFAULT,
    var importance: Int = NotificationManager.IMPORTANCE_DEFAULT
)

data class NotificationAttr(
        var id: Int = 0,
        var contentTitle: String? = null,
        var contentText: String? = null,
        var contentIntent: PendingIntent? = null,
        var priority: Int = NotificationCompat.PRIORITY_DEFAULT,
        var defaults: Int = NotificationCompat.DEFAULT_SOUND,
        var smallIcon: Int = R.drawable.ic_bell,
        var largeIcon: Bitmap? = null,
        var iconLevel: Int = 0,
        var autoCancel: Boolean = true,
        var ongoing: Boolean = false,
        var colorized: Boolean = false,
        var color: Int? = null,
        var content: RemoteViews? = null
) {
    fun setAttrs(): (NotificationCompat.Builder) -> Unit {
        return {
            this.apply {
                it.priority = priority
                it.setDefaults(defaults)
                it.setAutoCancel(autoCancel)
                it.setColorized(colorized)
                it.setOngoing(ongoing)
                if (iconLevel > 0) it.setSmallIcon(smallIcon, iconLevel) else it.setSmallIcon(smallIcon)
                color?.run { it.color = this }
                it.setStyle(NotificationCompat.DecoratedCustomViewStyle())
                content?.run { it.setCustomContentView(this) }
                contentTitle?.run { it.setContentTitle(this) }
                contentText?.run { it.setContentText(this) }
                contentIntent?.run { it.setContentIntent(this) }
                largeIcon?.run { it.setLargeIcon(this) }
            }
        }
    }
}

fun Context.notificationService() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

const val DEFAULT = "Default"