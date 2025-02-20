package com.example.servicesexample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class BgService : Service() {
    private var isServiceRunning = false
    override fun onBind(intent: Intent?): IBinder? = null


    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (!isServiceRunning) {
            isServiceRunning = true
            startBackgroundTask()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        isServiceRunning = false
        super.onDestroy()
    }

    fun startBackgroundTask() {
        thread(start = true) {
            while (isServiceRunning) {
                Log.d("BG_SERVICES", "BG Service Running!")
                Thread.sleep(1000)
            }
        }
    }
}