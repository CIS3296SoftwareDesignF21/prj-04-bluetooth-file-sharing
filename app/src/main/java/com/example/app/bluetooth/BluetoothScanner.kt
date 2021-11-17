package com.example.app.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.*
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.ParcelUuid
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.app.*
import com.example.app.bluetooth.data.ConnectionLiveData
import com.example.app.bluetooth.data.ScanLiveData


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class BluetoothScanner(private val context: Context, private val viewModel : ScanLiveData) {

    private var bluetoothLeScanner: BluetoothLeScanner? = null
    private lateinit var bluetoothAdapter: BluetoothAdapter
    private val ScanFilterService_UUID : ParcelUuid = ParcelUuid(SERVICE_UUID)


    private var handler: Handler? = null
    private var scanCallback: ScanCallback? = null


    /*
    * Use context to create 'android.bluetooth.BluetoothManager' then get the adapter.bluetoothLeScanner
    * also create a handler that will be used to terminate the scanning process.
    */
    fun initialize() {
        if (bluetoothLeScanner == null) {
            val manager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            bluetoothAdapter = manager.adapter
            bluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner
        }

        handler = Handler(Looper.myLooper()!!)
    }

    /*
    * First check if already scanning, if yes return. Then use the handler created in init to launch an asynchronous
    * process that will terminate scanning after SCAN_PERIOD_IN_MILLIS ms have passed. Then launch scanning and
    * pass the scanCallBack functions.
    */
    fun startScanning() {
        Log.d(CONTROLLER_TAG, "startScanning")

        if (scanCallback != null) {
            Log.d(CONTROLLER_TAG, "startScanning: already scanning")
            return
        }
        handler?.postDelayed({ stopScanning() }, SCAN_PERIOD_IN_MILLIS)
        scanCallback = SampleScanCallback()
        bluetoothLeScanner?.startScan(buildScanFilters(), buildScanSettings(), scanCallback)
    }


    /*
    * Stops scanning
    */
    private fun stopScanning() {
        Log.d(CONTROLLER_TAG, "stopScanning")
        bluetoothLeScanner?.stopScan(scanCallback)
        scanCallback = null

    }

    /*
    * Currently, does nothing, could be used to scan for specific type of devices or devices advertising a particular
    * serviceUuid.
    */
    private fun buildScanFilters(): List<ScanFilter> {
        val scanFilter = ScanFilter.Builder()
            .setServiceUuid(ScanFilterService_UUID)
            .build()
        Log.d(CONTROLLER_TAG, "buildScanFilters")
        return listOf(scanFilter)
    }

    private fun buildScanSettings() = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER).build()



    /*
    * This object is used to handle responses from the bluetooth device. The code in each overrided method
    * will be called when the asynchronous scanning process has something to report.
    */
    inner class SampleScanCallback() : ScanCallback() {

        /*onBatchScanResults passes all results to our viewModel via setItems method*/
//        override fun onBatchScanResults(results: MutableList<ScanResult>?) {
//            super.onBatchScanResults(results)
//            Log.d(CONTROLLER_TAG, "onBatchScanResults size: ${results?.size}")
//
//            results?.let { viewModel.setScanItems(it) }
//        }

        /*onScanResults passes one result to our viewModel via addSingleItems method*/
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            Log.d(CONTROLLER_TAG, "onScanResult: single")

            viewModel.addSingleScanItem(result)
        }

        /*onScanFailed logs an error if the scan was a failure*/
        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
            Log.d(CONTROLLER_TAG, "onScanFailed: errorCode $errorCode")
        }
    }

}