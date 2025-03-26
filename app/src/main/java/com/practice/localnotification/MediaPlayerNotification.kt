package com.practice.localnotification

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

fun media(context: Context){

    // For media player
    val style = androidx.media.app.NotificationCompat.MediaStyle()
        .setShowActionsInCompactView(0,1,2)

    // Pending intent for next
    val nextIntent = Intent(context, Next::class.java)
    val next = PendingIntent.getBroadcast(
        context, 6, nextIntent, PendingIntent.FLAG_MUTABLE
    )

    // Pending intent for previous
    val prevIntent = Intent(context, Prev::class.java)
    val prev = PendingIntent.getBroadcast(
        context, 4, prevIntent, PendingIntent.FLAG_MUTABLE
    )

    // Pending intent for Play and Pause
    val playPauseIntent = Intent(context, PlayAndPause::class.java)
    val playPause = PendingIntent.getBroadcast(
        context, 5, playPauseIntent, PendingIntent.FLAG_MUTABLE
    )
    val notification = NotificationCompat.Builder(context, DEFAULT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle(" title")
        .setContentText("desc")
        .addAction(R.drawable.ic_skip_previous, "prev", prev)
        .addAction(R.drawable.ic_play_circle, "play", playPause)
        .addAction(R.drawable.ic_skip_next, "next", next)
        .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.image))
        .setStyle(style)
        .build()

    //    Now send this notification to our channel
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(context, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, send notification
            NotificationManagerCompat.from(context).notify(12, notification)
        } else {
            // Permission not granted, you should request permission here (e.g., via an Activity or Fragment)
            // Optionally, notify the user that permission is required
            println("Permission for notifications not granted!")
        }
    } else {
        // On older versions, no need for permission check, send notification directly
        NotificationManagerCompat.from(context).notify(12, notification)
    }
}

// Broadcast class for receiver next
class Next: BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        Toast.makeText(p0, "next", Toast.LENGTH_LONG).show()
    }
}
// Broadcast class for receiver previous
class Prev: BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        Toast.makeText(p0, "prev", Toast.LENGTH_LONG).show()
    }
}
// Broadcast class for receiver play and pause
class PlayAndPause: BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        Toast.makeText(p0, "play and pause", Toast.LENGTH_LONG).show()
    }
}