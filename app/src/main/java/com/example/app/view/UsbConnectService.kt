package com.example.app.view

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.app.view.DownloadNotification
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.security.Provider



class UsbConnectService : Service() {

    private val downloadNotification: DownloadNotification = DownloadNotification(this)

    override fun onCreate() {
        super.onCreate()

        runBlocking {
            launch {
                startForeground(10, downloadNotification.downloadNotification())
                loadDataFake()
                stopSelf()
                downloadNotification.downloadDoneNotification()
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    private suspend fun loadDataFake() {

        downloadNotification.createNotificationChannel()

        for (i in 1..100) {
            //Log.i("test", "Done")
            downloadNotification.processDownloadNotification(i)
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}