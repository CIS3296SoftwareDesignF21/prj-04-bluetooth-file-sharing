package com.example.app.bluetooth

import android.app.Application
import android.bluetooth.*
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.app.CLIENT_TAG
import com.example.app.MESSAGE_UUID
import com.example.app.SERVICE_UUID
import com.example.app.messages.messages

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class BluetoothClient(val app : Application) {

    private var connectionState : Boolean = false
    private var connection : BluetoothDevice? = null

    private var messageCharacteristic: BluetoothGattCharacteristic? = null

    private var gattClient: BluetoothGatt? = null
    private var gattClientCallback: BluetoothGattCallback? = null

    private var i : Int = 0
    private var bytes : ByteArray? = null
    val offset = 500
    var num_packets : Int = 0
    var extraBytes : Int = -1


    fun connectToDevice(device: BluetoothDevice) {

        if(connectionState == false) {
            connection = device
            gattClientCallback = GattClientCallback()
            gattClient = device.connectGatt(app, false, gattClientCallback)
        }
    }

    fun sendMessage(message : messages) {

        num_packets = 0
        i = 0
        extraBytes = -1

        if (connectionState && messageCharacteristic != null) {

            if (message.byteArray != null){

                bytes = message.byteArray

                messageCharacteristic!!.writeType = BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE


                num_packets = bytes!!.size / offset
                extraBytes = bytes!!.size % offset

                if(num_packets > 1){num_packets-=1}

                messageCharacteristic!!.value = bytes!!.copyOfRange(0, offset)
                gattClient?.writeCharacteristic(messageCharacteristic)
                i++
            } else {
                val text = message.textContent
                Log.d(CLIENT_TAG,"Sending message: $text")
                extraBytes = 0
                messageCharacteristic!!.writeType = BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE

                messageCharacteristic!!.value = message.textContent.toByteArray(Charsets.UTF_8)
                gattClient?.writeCharacteristic(messageCharacteristic)
            }

        }
    }


    private inner class GattClientCallback : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            val isSuccess = status == BluetoothGatt.GATT_SUCCESS
            val isConnected = newState == BluetoothProfile.STATE_CONNECTED
            Log.d(CLIENT_TAG, "onConnectionStateChange: Client $gatt  success: $isSuccess connected: $isConnected")
            // try to send a message to the other device as a test
            if (isSuccess && isConnected) {
                connectionState = true
                // discover services
                gatt.discoverServices()
            }else{
                connectionState = false
            }
        }
        override fun onServicesDiscovered(discoveredGatt: BluetoothGatt, status: Int) {
            super.onServicesDiscovered(discoveredGatt, status)
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.d(CLIENT_TAG, "onServicesDiscovered: Have gatt $discoveredGatt")

                discoveredGatt.requestMtu(512)
                discoveredGatt.requestConnectionPriority(BluetoothGatt.CONNECTION_PRIORITY_HIGH)
                gattClient = discoveredGatt

                val service = discoveredGatt.getService(SERVICE_UUID)
                messageCharacteristic = service.getCharacteristic(MESSAGE_UUID)

            }
        }

        override fun onReliableWriteCompleted(gatt: BluetoothGatt?, status: Int) {
            super.onReliableWriteCompleted(gatt, status)

            Log.d(CLIENT_TAG,"Bluetooth reliable write status == $status")

        }

        override fun onCharacteristicWrite(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            super.onCharacteristicWrite(gatt, characteristic, status)

            Log.d(CLIENT_TAG, "Write $i of $num_packets")

            if(i<num_packets) {
                messageCharacteristic!!.value = bytes!!.copyOfRange(offset * i, offset * (i + 1))
                gattClient?.writeCharacteristic(messageCharacteristic)
                i++
            }else if (extraBytes > 0){
                messageCharacteristic!!.value = bytes!!.copyOfRange(offset*num_packets, offset*num_packets+extraBytes)
                gattClient?.writeCharacteristic(messageCharacteristic)
                extraBytes = 0
            } else if(extraBytes == 0){
                Log.d(CLIENT_TAG,"Sending done indication")
                extraBytes = -1
                messageCharacteristic!!.value = "DONE".toByteArray(Charsets.UTF_8)
                gattClient?.writeCharacteristic(messageCharacteristic)
            }

        }

    }

}