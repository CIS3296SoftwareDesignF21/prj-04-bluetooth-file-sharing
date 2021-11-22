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
import com.example.app.bluetooth.data.ConnectionLiveData


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
object BluetoothGATT {

    private var connection: BluetoothDevice? = null
    private val advertiser : BluetoothAdvertiser = BluetoothAdvertiser()

    private lateinit var bluetoothManager: BluetoothManager
    private lateinit var connectionLiveData: ConnectionLiveData

    private var app: Application? = null

    private var gattServer: BluetoothGattServer? = null
    private var gattServerCallback: BluetoothGattServerCallback? = null

    private var messageCharacteristic: BluetoothGattCharacteristic? = null

    private var connectionState: Boolean = false

    fun init(app: Application, viewModel : ConnectionLiveData){
        this.app = app
        connectionLiveData = viewModel
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

    fun postMessage(message: String) {
        Log.d(GATT_TAG, "Send a message")

        messageCharacteristic = gattServer?.getService(SERVICE_UUID)?.getCharacteristic(MESSAGE_UUID)


        messageCharacteristic?.let { characteristic ->

            characteristic.writeType = BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT

            val messageBytes = message.toByteArray(Charsets.UTF_8)
            characteristic.value = messageBytes

            gattServer?.let {

                if(connectionState) {
                    it.notifyCharacteristicChanged(connection, messageCharacteristic, false)
                } else {
                    Log.d(GATT_TAG, "sendMessage: no connection to send a message with")
                }

            }?: run {
                Log.d(GATT_TAG, "sendMessage: no gattServer connection to send a message with")
            }

        }?: run {
            Log.d(GATT_TAG, "sendMessage: no message Characteristic to send a message with")
        }

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
            BluetoothGattCharacteristic.PERMISSION_WRITE
        )
        service.addCharacteristic(messageCharacteristic)


        return service

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
                connectionLiveData.setConnectionState(connectionState)
                connectionLiveData.setConnection(connection)
            } else {
                connectionState = false
                connection = null
                connectionLiveData.setConnectionState(connectionState)
                connectionLiveData.setConnection(connection)

            }
        }

    }

}