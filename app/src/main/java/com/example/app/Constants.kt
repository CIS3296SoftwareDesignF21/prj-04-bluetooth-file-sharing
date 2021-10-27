package com.example.app

import android.Manifest
import java.util.*


const val LOCATION_FINE_PERM = Manifest.permission.ACCESS_FINE_LOCATION
const val PERMISSION_REQUEST_LOCATION = 101
const val SCAN_PERIOD_IN_MILLIS: Long = 90_000
const val DEVICE_NAME = "test_device"
val DEVICE_UUID : UUID = UUID(69,69)


/*Class TAGs*/

const val COM_TAG = "BluetoothCom"
const val CONTROLLER_TAG = "BluetoothController"
const val DATA_TAG = "BluetoothLiveData"
