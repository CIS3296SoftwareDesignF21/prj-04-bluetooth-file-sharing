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
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.bluetooth.data.ConnectionLiveData
import com.example.app.bluetooth.data.ScanLiveData
import com.example.app.fragments.adapter.DeviceAdapter
import com.example.app.databinding.FragmentScrollBinding
import com.example.app.fragments.data.SharedFragmentViewModel


/**
 * Displays remote nearby bluetooth devices via recycle viewer
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ScanFragment : Fragment() {

    private lateinit var binding: FragmentScrollBinding
    private lateinit var scannerAdapter: DeviceAdapter

    /*get reference to the viewModel that will hold BluetoothLiveData*/
    private val scanData: ScanLiveData by activityViewModels()
    private val serverViewModel: ConnectionLiveData by activityViewModels()
    val sharedViewModel : SharedFragmentViewModel by activityViewModels()


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

        val connected = Observer<Boolean> {
            connected -> if (connected == true) { Toast.makeText(context, "Officially connected", Toast.LENGTH_SHORT).show() }
            else {Toast.makeText(context, "Connection Broken", Toast.LENGTH_SHORT).show()}
        }

        /*tell our observers to start observing*/
        scanData.mutableDeviceListLiveData.observe(viewLifecycleOwner, results)
        serverViewModel.connectionState.observe(viewLifecycleOwner, connected)

    }


    /*
    * This method simply initializes the recycle view we are using in our first fragment
    * it also creates an instance of our DeviceAdapter class to use with the recycle view.
    */
    private fun setupRecyclerViewAdapter() {
        scannerAdapter = DeviceAdapter(sharedViewModel)
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = scannerAdapter
        }
    }

}

