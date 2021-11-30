package com.example.app.bluetooth.data

import android.bluetooth.le.ScanResult
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.DATA_TAG


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ScanLiveData: ViewModel() {

    //Device Lists Live Data
    private var mutableDeviceList: MutableList<ScanResult> = arrayListOf()
    val mutableDeviceListLiveData = MutableLiveData<MutableList<ScanResult>>()


    fun addSingleScanItem(item: ScanResult) {
        /**
         * In this particular case the data coming in may be duplicate. So check that only unique
         * elements are admitted: the device Id + device name should create a unique pair.
         * removeIf requires API level 24, so using removeAll here. But feel free to use any of
         * a number of options. Remove the previous element so to keep the latest timestamp
         */
        mutableDeviceList.removeAll {
            it.device.name == item.device.name
        }
        mutableDeviceList.add(item)
        val size : Int = mutableDeviceList.size

        mutableDeviceListLiveData.value = mutableDeviceList
    }
}