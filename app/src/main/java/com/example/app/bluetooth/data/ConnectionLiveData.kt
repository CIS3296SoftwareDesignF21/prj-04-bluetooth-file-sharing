package com.example.app.bluetooth.data

import android.bluetooth.BluetoothDevice
import android.bluetooth.le.ScanResult
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/*
 * BluetoothLiveData this class is a little confusing
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ConnectionLiveData(): ViewModel() {

    // LiveData for reporting connection requests
    private val mutableConnection = MutableLiveData<BluetoothDevice>()
    val connection = mutableConnection as LiveData<BluetoothDevice>

    // Properties for current chat device connection
    private val mutableConnectionState = MutableLiveData<Boolean>()
    val connectionState = mutableConnectionState as LiveData<Boolean>

    //Message Live Data
    private val mutableRemoteMessageLiveData =  MutableLiveData<String>()
    val messageRemoteLiveData = mutableRemoteMessageLiveData as LiveData<String>

    fun setConnection(device : BluetoothDevice?){
        mutableConnection.postValue(device)

    }

    fun setConnectionState(state : Boolean){
        mutableConnectionState.postValue(state)

    }

    fun setRemoteMessage(message : String){
        mutableRemoteMessageLiveData.postValue(message)

    }


}