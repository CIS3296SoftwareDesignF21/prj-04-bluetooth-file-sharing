package com.example.app

import android.bluetooth.le.ScanResult
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



/*
 * BluetoothLiveData this class is a little confusing
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class BluetoothLiveData(): ViewModel() {

    private var mutableDeviceList: MutableList<ScanResult> = arrayListOf()

    val mutableDeviceListLiveData = MutableLiveData<MutableList<ScanResult>>()

    fun setItems(mutableList: MutableList<ScanResult>) {
        if (mutableList != mutableDeviceList) {

            mutableDeviceList = mutableList

            mutableDeviceListLiveData.value = mutableDeviceList
        }
    }

    fun addSingleItem(item: ScanResult) {
        /**
         * In this particular case the data coming in may be duplicate. So check that only unique
         * elements are admitted: the device Id + device name should create a unique pair.
         * removeIf requires API level 24, so using removeAll here. But feel free to use any of
         * a number of options. Remove the previous element so to keep the latest timestamp
         */
        mutableDeviceList.removeAll {
            it.device.name == item.device.name && it.device.address == item.device.address
        }
        mutableDeviceList.add(item)
        val size : Int = mutableDeviceList.size
        Log.d(DATA_TAG, "addSingleItem: deciveList is size $size")

        mutableDeviceListLiveData.value = mutableDeviceList
    }


}