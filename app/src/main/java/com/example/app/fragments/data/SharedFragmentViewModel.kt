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

    private val mutableMessage = MutableLiveData<messages?>()
    val message = mutableMessage as LiveData<messages>

    val displayMessage = MutableLiveData<messages>()

    var messageList = MutableLiveData<MutableList<messages>>()

    fun setMessage(message : messages){
        this.mutableMessage.value = message
    }

    fun setEmptyMessage(){
        this.mutableMessage.value = messages(
            null,
            null,
            null,
            null,
            0,
            null,
            0
        )
    }

    fun setMessageList(list : MutableList<messages>){
        messageList.postValue(list)
    }

    fun setSend(device : BluetoothDevice){

        mutableScreen.value = 1
        mutableConnection.value = device

    }

    fun setMessageView(){
        mutableScreen.value = 2
    }

    fun setClient(message : messages){
        displayMessage.value = message
        mutableScreen.value = 3
    }


    fun setHome(){
        mutableScreen.value = 0
    }

}