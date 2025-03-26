package com.practice.localnotification

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat

const val REPLY = "reply"

fun message(context: Context) {


    val replyInput = RemoteInput.Builder(REPLY).setLabel("Reply").build()

    val intent = Intent(context, MessageReceiver::class.java)

    val pendingIntent = PendingIntent.getBroadcast(
        context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
    )

    val action = NotificationCompat.Action.Builder(R.drawable.id_reply, "Reply", pendingIntent)
        .addRemoteInput(replyInput)

    val person = Person.Builder().setName("Prianka").build()
    val style = NotificationCompat.MessagingStyle(person)
        .setConversationTitle("conversation title")
        .addMessage("text 1", System.currentTimeMillis(), person)
        .addMessage("text 2", System.currentTimeMillis(), person)

    val notification = NotificationCompat.Builder(context, DEFAULT)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("title")
        .setContentText("desc")
        .setStyle(style)
        .addAction(action.build())
        .build()


    //    Now send this notification to our channel
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(context, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, send notification
            NotificationManagerCompat.from(context).notify(3, notification)
        } else {
            // Permission not granted, you should request permission here (e.g., via an Activity or Fragment)
            // Optionally, notify the user that permission is required
            println("Permission for notifications not granted!")
        }
    } else {
        // On older versions, no need for permission check, send notification directly
        NotificationManagerCompat.from(context).notify(3, notification)
    }


}


class MessageReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        RemoteInput.getResultsFromIntent(intent)?.getCharSequence(REPLY)?.let {text->

            val replyInput = RemoteInput.Builder(REPLY).setLabel("Reply").build()

            val replyIntent = Intent(context, MessageReceiver::class.java)

            val pendingIntent = PendingIntent.getBroadcast(
                context, 1, replyIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )

            val action = NotificationCompat.Action.Builder(R.drawable.id_reply, "Reply", pendingIntent)
                .addRemoteInput(replyInput)

            val person = Person.Builder().setName("Prianka").build()
            val person2 = Person.Builder().setName("Pranti").build()
            val style = NotificationCompat.MessagingStyle(person)
                .setConversationTitle("conversation title")
                .addMessage("text 1", System.currentTimeMillis(), person)
                .addMessage("text 2", System.currentTimeMillis(), person)
                .addMessage(text,System.currentTimeMillis(),person2)

            val notification = NotificationCompat.Builder(context, DEFAULT)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("title")
                .setContentText("desc")
                .setStyle(style)
                .addAction(action.build())
                .build()


            //    Now send this notification to our channel
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(context, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, send notification
                    NotificationManagerCompat.from(context).notify(3, notification)
                } else {
                    // Permission not granted, you should request permission here (e.g., via an Activity or Fragment)
                    // Optionally, notify the user that permission is required
                    println("Permission for notifications not granted!")
                }
            } else {
                // On older versions, no need for permission check, send notification directly
                NotificationManagerCompat.from(context).notify(3, notification)
            }

        }
    }
}