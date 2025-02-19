package com.example.servicesexample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.servicesexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.apply {

            btnStartBackgroundService.setOnClickListener {
                startService(Intent(this@MainActivity, BgService::class.java))
            }

            btnStopService.setOnClickListener {
                stopService(Intent(this@MainActivity, BgService::class.java))
            }

            btnStartForegroundService.setOnClickListener {
                // they need notification object
                // first we create notification object for creation of notification object we need 3 things
                // 1. create notification channel
                // 2. pending Intent
                // 3. notification object
                if (ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        android.Manifest.permission.POST_NOTIFICATIONS
                    ) == android.content.pm.PackageManager.PERMISSION_GRANTED
                ) {
                    startForegroundService(Intent(this@MainActivity, ForegroundService::class.java))

                } else {
                    ActivityCompat.requestPermissions(
                        this@MainActivity,
                        arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                        0
                    )
                }
            }
        }
    }
}