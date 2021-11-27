package com.example.app.fragments.data

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedFragmentViewModel : ViewModel() {

    private val mutableConnection = MutableLiveData<BluetoothDevice>()
    val connection = mutableConnection as LiveData<BluetoothDevice>

    // Properties for current chat device connection
    private val mutableScreen = MutableLiveData<Int>()
    val screen = mutableScreen as LiveData<Int>

    fun setClient(device : BluetoothDevice){

        mutableScreen.value = 3
        mutableConnection.value = device

    }

    fun setSend(){
        mutableScreen.value = 1
    }

    fun setSettings(){
        mutableScreen.value = 2
    }

    fun setHome(){
        mutableScreen.value = 0
    }

}