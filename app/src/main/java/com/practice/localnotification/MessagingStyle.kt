package com.practice.localnotification

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.content.ContextCompat

fun message(context: Context){

    val person = Person.Builder().setName("Prianka").build()
    val style = NotificationCompat.MessagingStyle(person)
        .setConversationTitle("Conversion title")
        .addMessage("text 1", System.currentTimeMillis(), person)
        .addMessage("text 2", System.currentTimeMillis(), person)

    val notification = NotificationCompat.Builder(context, DEFAULT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("Title")
        .setContentText("desc")
        .setStyle(style)
        .build()

    //    Now send this notification to our channel
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(context, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, send notification
            NotificationManagerCompat.from(context).notify(10, notification)
        } else {
            // Permission not granted, you should request permission here (e.g., via an Activity or Fragment)
            // Optionally, notify the user that permission is required
            println("Permission for notifications not granted!")
        }
    } else {
        // On older versions, no need for permission check, send notification directly
        NotificationManagerCompat.from(context).notify(10, notification)
    }

}