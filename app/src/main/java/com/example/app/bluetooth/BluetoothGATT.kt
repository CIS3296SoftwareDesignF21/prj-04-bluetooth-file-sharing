/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.app.bluetooth

import android.app.Application
import android.bluetooth.*
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.app.*
import com.example.app.fragments.data.SharedFragmentViewModel
import com.example.app.messages.messages


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
object BluetoothGATT {

    private lateinit var sharedViewModel : SharedFragmentViewModel

    private var connection: BluetoothDevice? = null
    private val advertiser : BluetoothAdvertiser = BluetoothAdvertiser()

    private lateinit var bluetoothManager: BluetoothManager

    private var app: Application? = null

    private var gattServer: BluetoothGattServer? = null
    private var gattServerCallback: BluetoothGattServerCallback? = null

    private var connectionState: Boolean = false

    private val _messageList : MutableList<messages> = mutableListOf()


    private var temp_message : ByteArray? = null

    fun init(app: Application, sharedViewModel: SharedFragmentViewModel){
        this.sharedViewModel = sharedViewModel
        this.app = app
        bluetoothManager = app.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    }

    fun startServer() {
        setupGattServer(app!!)
        advertiser.startAdvertisement()
    }

    fun stopServer() {
        gattServer?.close()
        advertiser.stopAdvertising()
    }


    /**
     * Function to setup a local GATT server.
     * This requires setting up the available services and characteristics that other devices
     * can read and modify.
     */
    private fun setupGattServer(app: Application) {
        gattServerCallback = GattServerCallback()

        gattServer = bluetoothManager.openGattServer(
            app,
            gattServerCallback
        ).apply {
            addService(setupGattService())
        }

    }

    /**
     * Function to create the GATT Server with the required characteristics and descriptors
     */
    private fun setupGattService(): BluetoothGattService {

        // Setup gatt service
        val service = BluetoothGattService(SERVICE_UUID, BluetoothGattService.SERVICE_TYPE_PRIMARY)

        // need to ensure that the property is writable and has the write permission
        val messageCharacteristic = BluetoothGattCharacteristic(
            MESSAGE_UUID,
            BluetoothGattCharacteristic.PROPERTY_WRITE,
            BluetoothGattCharacteristic.PERMISSION_WRITE,
        )

        messageCharacteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE)

        service.addCharacteristic(messageCharacteristic)


        return service

    }

    fun handleInputMessage(device: BluetoothDevice, value: ByteArray){

        temp_message = null

        var message : messages? = null


        if(value.size < 1000){

            message = messages(
                value.toString(Charsets.UTF_8),
                null,
                null,
                device.name,
                null,
                0,
                null,
                value!!.size
            )

        } else {

            message = messages(
                null,
                null,
                value!!,
                device.name,
                null,
                0,
                null,
                value!!.size
            )
        }

        val size = value!!.size

        val name = device.name

        Log.d(GATT_TAG, "onCharacteristicWriteRequest: Have message: size: \"$size\" from $device")

        message?.let {
            _messageList.add(it)
            sharedViewModel.setMessageList(_messageList)
        }

    }

    /**
     * Custom callback for the Gatt Server this device implements
     *
     */
    private class GattServerCallback : BluetoothGattServerCallback() {

        override fun onConnectionStateChange(device: BluetoothDevice, status: Int, newState: Int) {
            super.onConnectionStateChange(device, status, newState)
            val isSuccess = status == BluetoothGatt.GATT_SUCCESS
            val isConnected = newState == BluetoothProfile.STATE_CONNECTED
            Log.d(
                GATT_TAG,
                "onConnectionStateChange: Server $device ${device.name} success: $isSuccess connected: $isConnected"
            )
            if (isSuccess && isConnected) {
                connectionState = true
                connection = device

            } else {
                connectionState = false
                connection = null

            }
        }


        override fun onCharacteristicWriteRequest(
            device: BluetoothDevice?,
            requestId: Int,
            characteristic: BluetoothGattCharacteristic,
            preparedWrite: Boolean,
            responseNeeded: Boolean,
            offset: Int,
            value: ByteArray
        ) {
            super.onCharacteristicWriteRequest(
                device,
                requestId,
                characteristic,
                preparedWrite,
                responseNeeded,
                offset,
                value
            )
            Log.d("SVC", "BluetoothGattServerCallback.onCharacteristicWriteRequest with " + value.size + " bytes")

            if ("DONE" == value.toString(Charsets.UTF_8)){
                handleInputMessage(device!!, temp_message!!)
            } else {
                handleInputFragment(value)
            }

        }

    }


    private fun handleInputFragment(value: ByteArray) {
        Log.d("SVC", "handling input Fragment")

        if(temp_message == null){
            temp_message = value
        }else{
            temp_message = temp_message!! + value
        }

    }
}