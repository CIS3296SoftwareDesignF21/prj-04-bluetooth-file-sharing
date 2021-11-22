package com.example.app.bluetooth

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.example.app.bluetooth.data.ConnectionLiveData
import com.example.app.bluetooth.data.ScanLiveData

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class NetworkDaemon : Service() {

    private lateinit var bluetoothScanner : BluetoothScanner
    private lateinit var bluetoothClient : BluetoothClient
    private lateinit var scanLiveData : ScanLiveData
    private lateinit var connectionLiveData : ConnectionLiveData


    override fun onCreate() {
        super.onCreate()

        bluetoothClient = BluetoothClient(connectionLiveData, this.application)

        bluetoothScanner = BluetoothScanner(this,scanLiveData)
        bluetoothScanner.initialize()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        BluetoothGATT.startServer()
        bluetoothScanner.startScanning()



        return super.onStartCommand(intent, flags, startId)
    }


    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}