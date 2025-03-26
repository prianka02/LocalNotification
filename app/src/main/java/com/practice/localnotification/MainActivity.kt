package com.practice.localnotification

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practice.localnotification.ui.theme.LocalNotificationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LocalNotificationsTheme {
                 Column(
                     Modifier.fillMaxSize(),
                     verticalArrangement = Arrangement.Center,
                     horizontalAlignment = Alignment.CenterHorizontally
                 ) {
                     Button(
                         onClick = {
                             simpleNotification(this@MainActivity)
                         }
                     ) {
                         Text(text = "Simple Notifications")
                     }

                     Spacer(Modifier.height(12.dp))

                     Button(
                         onClick = {
                             silentNotification(this@MainActivity)
                         }
                     ) {
                         Text(text = "Silent Notifications")
                     }

                     Spacer(Modifier.height(12.dp))

                     Button(
                         onClick = {
                             urgentNotification(this@MainActivity)
                         }
                     ) {
                         Text(text = "Urgent Notifications")
                     }

                     Spacer(Modifier.height(12.dp))

                     Button(
                         onClick = {
                             groupNotification(this@MainActivity)
                         }
                     ) {
                         Text(text = "Group Notifications")
                     }

                     Spacer(Modifier.height(12.dp))

                     Button(
                         onClick = {
                             actionNotification(this@MainActivity)
                         }
                     ) {
                         Text(text = "Action Notifications")
                     }

                     Spacer(Modifier.height(12.dp))

                     Button(
                         onClick = {
                             bigTextStyle(this@MainActivity)
                         }
                     ) {
                         Text(text = "Big Text Notifications")
                     }

                     Spacer(Modifier.height(12.dp))

                     Button(
                         onClick = {
                             bigPictureStyle(this@MainActivity)
                         }
                     ) {
                         Text(text = "Big Picture Notifications")
                     }

                     Spacer(Modifier.height(12.dp))

                     Button(
                         onClick = {
                             inboxStyle(this@MainActivity)
                         }
                     ) {
                         Text(text = "Inbox Notifications")
                     }

                     Spacer(Modifier.height(12.dp))

                     Button(
                         onClick = {
                             message(this@MainActivity)
                         }
                     ) {
                         Text(text = "Message Notifications")
                     }
                 }

            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    LocalNotificationsTheme {
//     }
//}