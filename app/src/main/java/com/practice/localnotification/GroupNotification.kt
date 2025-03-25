package com.practice.localnotification

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

const val GROUP = "group"

fun groupNotification(context: Context){
    val notification = NotificationCompat.Builder(context, DEFAULT)
        .setSmallIcon(R.drawable.id_reply)
        .setContentTitle("First Title")
        .setContentText("First Description")
        .setGroup(GROUP)
        .build()

    val secondNotification = NotificationCompat.Builder(context, DEFAULT)
        .setSmallIcon(R.drawable.ic_play)
        .setContentTitle("Second Title")
        .setContentText("Second Description")
        .setGroup(GROUP)
        .build()

    val groupBuilder = NotificationCompat.Builder(context, DEFAULT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("group title")
        .setContentText("Group Text")
        .setGroup(GROUP)
        .setGroupSummary(true)
        .build()


    //    Now send this notification to our channel
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(context, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, send notification
            NotificationManagerCompat.from(context).apply {
                notify(4, notification)
                notify(5, secondNotification)
                notify(6, groupBuilder)
            }
        } else {
            // Permission not granted, you should request permission here (e.g., via an Activity or Fragment)
            // Optionally, notify the user that permission is required
            println("Permission for notifications not granted!")
        }
    } else {
        // On older versions, no need for permission check, send notification directly
        NotificationManagerCompat.from(context).apply {
            notify(4, notification)
            notify(5, secondNotification)
            notify(6, groupBuilder)
        }
    }
}