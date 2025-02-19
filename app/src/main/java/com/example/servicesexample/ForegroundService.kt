package com.example.servicesexample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class ForegroundService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startLoggerForegroundService()
        return super.onStartCommand(intent, flags, startId)
    }


    fun startLoggerForegroundService() {
        createNotificationChannel()
        val notification = createNotification()
        startForeground(111, notification)
    }

    fun getPendingIntent() : PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE)
    }

    fun createNotificationChannel() : NotificationChannel {
        // first we create notification channel
        val channel = NotificationChannel("1", "Services Channel", NotificationManager.IMPORTANCE_DEFAULT)

        // notification manager
        val notificationManager = getSystemService(NotificationManager::class.java) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        return channel
    }

    fun createNotification() : Notification {
        val notification = NotificationCompat.Builder(this, "1")
            .setContentTitle("Services Example")
            .setContentText("Foreground Service running")
            .setContentIntent(getPendingIntent())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .build()

        return notification
    }

}