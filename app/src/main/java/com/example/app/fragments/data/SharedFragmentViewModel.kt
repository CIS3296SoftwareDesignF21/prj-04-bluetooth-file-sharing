package com.example.app.fragments.data

import android.bluetooth.BluetoothDevice
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.messages.messages

class SharedFragmentViewModel : ViewModel() {

    private val mutableConnection = MutableLiveData<BluetoothDevice>()
    val connection = mutableConnection as LiveData<BluetoothDevice>

    // Properties for current chat device connection
    private val mutableScreen = MutableLiveData<Int>()
    val screen = mutableScreen as LiveData<Int>

    private val mutableMessage = MutableLiveData<messages>()
    val message = mutableMessage as LiveData<messages>

    fun setMessage(message : messages){
        this.mutableMessage.value = message
    }

    fun setSend(device : BluetoothDevice){

        mutableScreen.value = 2
        mutableConnection.value = device

    }

    fun setClient(){
        mutableScreen.value = 3
    }

    fun setSettings(){
        mutableScreen.value = 2
    }

    fun setHome(){
        mutableScreen.value = 0
    }

}