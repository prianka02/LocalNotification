package com.practice.localnotification

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

fun simpleNotification(context: Context){
    // Create Notification
    val notification = NotificationCompat.Builder(context, DEFAULT)
        .setSmallIcon(R.drawable.ic_launcher_background)  // Mandatory, otherwise it will crash
        .setContentTitle("title")
        .setContentText("description")
        .build()

//    Now send this notification to our channel
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(context, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, send notification
            NotificationManagerCompat.from(context).notify(1, notification)
        } else {
            // Permission not granted, you should request permission here (e.g., via an Activity or Fragment)
            // Optionally, notify the user that permission is required
            println("Permission for notifications not granted!")
        }
    } else {
        // On older versions, no need for permission check, send notification directly
        NotificationManagerCompat.from(context).notify(1, notification)
    }


}