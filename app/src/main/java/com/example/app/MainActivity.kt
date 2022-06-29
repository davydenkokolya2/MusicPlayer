package com.example.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.app.presentation.view.UsbConnectService
import com.example.app.view.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var broadcastReceiever: BroadcastReceiver


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        createReceiver(this)
        registerReceiver()
    }

    private fun createReceiver(context: Context) {
        broadcastReceiever = object : BroadcastReceiver() {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onReceive(p0: Context, p1: Intent) {
                startForegroundService(Intent(context, UsbConnectService::class.java))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun registerReceiver() {
        val intentFilter = IntentFilter(Intent.ACTION_POWER_CONNECTED)
        registerReceiver(broadcastReceiever, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiever)
    }
}