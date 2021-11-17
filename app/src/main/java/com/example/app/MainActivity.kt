package com.example.app

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import com.example.app.bluetooth.BluetoothScanner
import com.example.app.bluetooth.BluetoothGATT
import com.example.app.bluetooth.data.ConnectionLiveData
import com.example.app.bluetooth.data.ScanLiveData
import com.example.app.databinding.ActivityMainBinding
import com.example.app.fragments.*
import com.example.app.fragments.data.SharedFragmentViewModel

@RequiresApi(Build.VERSION_CODES.M)
//@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MainActivity : AppCompatActivity() {

    private val sharedViewModel : SharedFragmentViewModel by viewModels()
    private val serverViewModel: ConnectionLiveData by viewModels()
    private val scanData : ScanLiveData by viewModels()

    private lateinit var btScanner: BluetoothScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hides action bar
        supportActionBar!!.hide()

        //gets view binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*create an instance of BluetoothManager and initialize*/
        btScanner= BluetoothScanner(this,scanData)
        btScanner.initialize()

        BluetoothGATT.init(application, serverViewModel)

        //sets initial fragment
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<ScanFragment>(R.id.fragment_container)
        }

        sharedViewModel.screen.observe(this, Observer { curScreen ->
            // Perform an action with the latest item data

            when(curScreen) {
                0 -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<ScanFragment>(R.id.fragment_container)
                    }
                }
                1 -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<SendFragment>(R.id.fragment_container)
                    }

                }
                2 -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<SettingFragment>(R.id.fragment_container)
                    }

                }
                3 -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<ClientFragment>(R.id.fragment_container)
                    }
                }
                else -> {}
            }



        })



        binding.fab.setOnClickListener {

            sharedViewModel.setSend()

        }

        binding.settings.setOnClickListener{

            sharedViewModel.setSettings()

        }

    }

    override fun onStart() {
        super.onStart()

        requestLocationPermission()

    }

    override fun onStop() {
        super.onStop()

        BluetoothGATT.stopServer()
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
            val alertDialogBuilder = AlertDialog.Builder(this)
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
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    btScanner.startScanning()
                    BluetoothGATT.startServer()
                }
            }
            else -> Toast.makeText(
                this,
                getString(R.string.loc_req_denied_msg),
                Toast.LENGTH_LONG
            ).show()
        }
    }

}
