package com.example.app.bluetooth

import android.app.Application
import android.bluetooth.*
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.app.CLIENT_TAG
import com.example.app.bluetooth.data.ConnectionLiveData
import com.example.app.GATT_TAG
import com.example.app.MESSAGE_UUID
import com.example.app.SERVICE_UUID

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class BluetoothClient(val connectionLiveData : ConnectionLiveData, val app : Application) {

    private var connectionState : Boolean = false
    private var connection : BluetoothDevice? = null

    private var messageCharacteristic: BluetoothGattCharacteristic? = null

    private var gattClient: BluetoothGatt? = null
    private var gattClientCallback: BluetoothGattCallback? = null


    fun connectToDevice(device: BluetoothDevice) {
            connection = device
            gattClientCallback = GattClientCallback()
            gattClient = device.connectGatt(app, false, gattClientCallback)
    }

//    fun sendMessage(message: String): Boolean {
//        Log.d(GATT_TAG, "Send a message")
//
//        messageCharacteristic?.let { characteristic ->
//            characteristic.writeType = BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
//
//            val messageBytes = message.toByteArray(Charsets.UTF_8)
//            characteristic.value = messageBytes
//
//            gattClient?.let {
//                val success = it.writeCharacteristic(messageCharacteristic)
//                Log.d(GATT_TAG, "onServicesDiscovered: message send: $success")
//
//            } ?: run {
//                Log.d(GATT_TAG, "sendMessage: no gatt connection to send a message with")
//            }
//
//        } ?: run {
//            Log.d(GATT_TAG, "sendMessage: no message characteristic found")
//        }
//        return false
//    }

    private inner class GattClientCallback : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            val isSuccess = status == BluetoothGatt.GATT_SUCCESS
            val isConnected = newState == BluetoothProfile.STATE_CONNECTED
            Log.d(CLIENT_TAG, "onConnectionStateChange: Client $gatt  success: $isSuccess connected: $isConnected")
            // try to send a message to the other device as a test
            if (isSuccess && isConnected) {
                gattClient = gatt
                connectionState = true
                // discover services
                gatt.discoverServices()
            }
        }

        override fun onServicesDiscovered(discoveredGatt: BluetoothGatt, status: Int) {
            super.onServicesDiscovered(discoveredGatt, status)
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.d(CLIENT_TAG, "onServicesDiscovered: Have gatt $discoveredGatt")
                val service = discoveredGatt.getService(SERVICE_UUID)
                messageCharacteristic = service.getCharacteristic(MESSAGE_UUID)
            }
        }

        override fun onCharacteristicWrite(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            super.onCharacteristicWrite(gatt, characteristic, status)
            if (characteristic?.uuid == MESSAGE_UUID) {

                val message = characteristic?.value.toString(Charsets.UTF_8)

                Log.d(CLIENT_TAG, "onCharacteristicWriteRequest: Have message: \"$message\"")

                message?.let {
                    connectionLiveData.setRemoteMessage(it)
                }
            }

        }
    }
}