/*
 * Copyright (C) 2021 The Android Open Source Project
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

package com.example.app.fragments

import android.bluetooth.le.ScanResult
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.BluetoothLiveData
import com.example.app.LOCATION_FINE_PERM
import com.example.app.PERMISSION_REQUEST_LOCATION
import com.example.app.R
import com.example.app.bluetooth.BluetoothController
import com.example.app.fragments.adapter.DeviceAdapter
import com.example.app.databinding.FragmentScrollBinding


/**
 * Displays remote nearby bluetooth devices via recycle viewer
 */

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ScrollFragment : Fragment() {

    private lateinit var binding: FragmentScrollBinding
    private lateinit var scannerAdapter: DeviceAdapter
    private lateinit var btController: BluetoothController

    /*get reference to the viewModel that will hold BluetoothLiveData*/
    private val viewModel: BluetoothLiveData by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScrollBinding.inflate(inflater, container, false)
        return binding.root
    }

    /*
    * In onViewCreated first we set up the recycle view. Then create a listener to the data
    * where scan results will be put. Once we have a listener we create an instance of BluetoothController
    * and pass in the viewModel that holds our list where we want scan results. Then we initialize
    * BluetoothController and call requestLocationPermission which in turn calls startScanning a method from
    * our BluetoothController.
    */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*setup recycle view*/
        setupRecyclerViewAdapter()

        /*create listener to scan result data*/
        val results = Observer<MutableList<ScanResult>> { results ->

            /*this calls the recycle view adapter to update our list*/
            scannerAdapter.updateView(results)

        }

        /*tell our observer to start observing 'mutableDeviceListLiveData'*/
        viewModel.mutableDeviceListLiveData.observe(viewLifecycleOwner, results)

        /*create an instance of BluetoothManager and initialize*/
        btController= BluetoothController(requireContext(),viewModel)
        btController.initialize()

        /*get location permission and in turn call startScanning*/
        requestLocationPermission()
    }


    /*
    * This method simply initializes the recycle view we are using in our first fragment
    * it also creates an instance of our DeviceAdapter class to use with the recycle view.
    */
    private fun setupRecyclerViewAdapter() {
        scannerAdapter = DeviceAdapter()
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = scannerAdapter
        }
    }


    /*
    * All code below here is used to get permission for location
    * the requestLocationPermission method is used in onViewCreated
    * to get this permission from the user and call startScanning.
    *
    * Before Android N, Bluetooth didn't need location permission in order to start scanning.
    * But between Android N and R location permission is required for Bluetooth scanning.
    * The code below requests location access if it's required and has not yet been granted.
    */
    private fun requestLocationPermission() {
        if (shouldShowRequestPermissionRationale(LOCATION_FINE_PERM)) {
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            with(alertDialogBuilder) {
                setTitle(getString(R.string.loc_req_title))
                setMessage(getString(R.string.loc_req_msg))
                setPositiveButton(getString(R.string.okay)) { _, _ -> makeLocationRequest() }
            }
            alertDialogBuilder.create().show()
        } else {
            makeLocationRequest()
        }
    }

    private fun makeLocationRequest() {
        requestPermissions(
            arrayOf(LOCATION_FINE_PERM),
            PERMISSION_REQUEST_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) { btController.startScanning()
                }
            }
            else -> Toast.makeText(
                requireContext(),
                getString(R.string.loc_req_denied_msg),
                Toast.LENGTH_LONG
            ).show()
        }
    }

}

