package com.example.app.fragments

import android.bluetooth.BluetoothDevice
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.app.bluetooth.BluetoothClient
import com.example.app.bluetooth.data.ConnectionLiveData
import com.example.app.databinding.FragmentClientBinding
import com.example.app.fragments.data.SharedFragmentViewModel


/*
*  This fragment creates a connection to the selected device and updates the UI whenever data is updated on the
*  connection. First we create the connection with bluetooth client. Then the client updates the connectionlivedata
*  which in turn trigger the UI to update with the new data to display to the user.
*
*/
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ClientFragment : Fragment() {

    private val sharedViewModel : SharedFragmentViewModel by activityViewModels()

    private val clientViewModel : ConnectionLiveData by viewModels()

    private lateinit var binding: FragmentClientBinding

    private lateinit var btClient : BluetoothClient

    private lateinit var displayDevice : BluetoothDevice

    private var messageList : String = ""

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        val app = requireActivity().application

        btClient = BluetoothClient(clientViewModel,app)

        displayDevice = sharedViewModel.connection!!.value!!

        val deviceName = displayDevice.name
        Log.d("ClientFragment", "device: $deviceName")

        btClient.connectToDevice(displayDevice)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve and inflate the layout for this fragment
        binding = FragmentClientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.deviceTitle.text = displayDevice.name
        binding.messageBox.text = messageList

        val message = Observer<String> {

            messageList = messageList + it + "\n"

            binding.messageBox.text = messageList

        }

        val connected = Observer<Boolean> {
                connected -> if (connected == true) { Toast.makeText(context, "Officially connected", Toast.LENGTH_SHORT).show() }
        else {Toast.makeText(context, "Connection Broken", Toast.LENGTH_SHORT).show()}
        }

        /*tell our observer to start observing*/
        clientViewModel.messageRemoteLiveData.observe(viewLifecycleOwner, message)
        clientViewModel.connectionState.observe(viewLifecycleOwner, connected)

    }

    companion object{
        fun newInstance(): ClientFragment {
            val args = Bundle()
            val fragment = ClientFragment()
            fragment.arguments = args
            return fragment
        }
    }

}