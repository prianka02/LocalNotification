package com.practice.localnotification

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

fun custom(context: Context){

    val smallCustomView = RemoteViews(context.packageName, R.layout.small_custom_layout)
    val customView = RemoteViews(context.packageName, R.layout.custom_layout)

    val notification = NotificationCompat.Builder(context, DEFAULT)
        .setCustomContentView(smallCustomView)
        .setCustomBigContentView(customView)
        .setSmallIcon(R.drawable.ic_launcher_background)  // must be added or crashed
        .build()

    //    Now send this notification to our channel
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(context, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, send notification
            NotificationManagerCompat.from(context).notify(14, notification)
        } else {
            // Permission not granted, you should request permission here (e.g., via an Activity or Fragment)
            // Optionally, notify the user that permission is required
            println("Permission for notifications not granted!")
        }
    } else {
        // On older versions, no need for permission check, send notification directly
        NotificationManagerCompat.from(context).notify(14, notification)
    }

}