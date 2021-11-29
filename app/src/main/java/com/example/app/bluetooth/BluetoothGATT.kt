package com.example.app.bluetooth

import android.app.Application
import android.bluetooth.*
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app.*
import com.example.app.bluetooth.data.ConnectionLiveData
import com.example.app.messages.messages


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

    private lateinit var _remoteMessage : MutableLiveData<messages>
    var remoteMessage = _remoteMessage as LiveData<messages>

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

        override fun onCharacteristicWriteRequest(
            device: BluetoothDevice?,
            requestId: Int,
            characteristic: BluetoothGattCharacteristic?,
            preparedWrite: Boolean,
            responseNeeded: Boolean,
            offset: Int,
            value: ByteArray?
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

            val message = value?.toString(Charsets.UTF_8)

            Log.d(GATT_TAG, "onCharacteristicWriteRequest: Have message: \"$message\"")
            message?.let {
                // _remoteMessage.postValue(it)
            }

        }

    }

}