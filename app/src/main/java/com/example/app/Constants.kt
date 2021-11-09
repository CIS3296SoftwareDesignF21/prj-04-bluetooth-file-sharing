package com.example.app

import android.Manifest
import java.util.*
import android.bluetooth.BluetoothDevice

const val LOCATION_FINE_PERM = Manifest.permission.ACCESS_FINE_LOCATION
const val PERMISSION_REQUEST_LOCATION = 101
const val SCAN_PERIOD_IN_MILLIS: Long = 90_000
const val ADVERTISE_PERIOD_IN_MILLIS: Long = 90_000
const val DEVICE_NAME = "test_device"


/*Class TAGs*/
const val COM_TAG = "BluetoothCom"
const val CONTROLLER_TAG = "BluetoothScanner"
const val DATA_TAG = "BluetoothLiveData"
const val AD_TAG = "BluetoothAdvertiser"


/*Channel Connection States*/
const val IDLE = 0
const val AWAITING = 1
const val CONNECTING = 2
const val CONNECTED = 3

/**
 * Constants for use in the Bluetooth LE Chat sample
 */
/**
 * UUID identified with this app - set as Service UUID for BLE Chat.
 *
 * Bluetooth requires a certain format for UUIDs associated with Services.
 * The official specification can be found here:
 * [://www.bluetooth.org/en-us/specification/assigned-numbers/service-discovery][https]
 */
val SERVICE_UUID: UUID = UUID.fromString("0000b81d-0000-1000-8000-00805f9b34fb")

/**
 * UUID for the message
 */
val MESSAGE_UUID: UUID = UUID.fromString("7db3e235-3608-41f3-a03c-955fcbd2ea4b")

/**
 * UUID to confirm device connection
 */
val CONFIRM_UUID: UUID = UUID.fromString("36d4dc5c-814b-4097-a5a6-b93b39085928")