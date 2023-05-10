package com.example.testingapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: NotificationCompat.Builder
    private val channelId = "my_channel_01"
    private val description = "Test notification"

    private lateinit var btnNotify: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNotify = findViewById(R.id.button)

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                channelId,
                description,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        btnNotify.setOnClickListener {
            showNotification()
        }
    }

    private fun showNotification() {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        builder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("AGRO")
            .setContentText("Registration Successful")
            .setSmallIcon(R.drawable.bell)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        notificationManager.notify(1234, builder.build())
    }
}