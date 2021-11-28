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
import com.example.app.bluetooth.BluetoothGATT
import com.example.app.bluetooth.data.ConnectionLiveData
import com.example.app.databinding.FragmentClientBinding
import com.example.app.fragments.data.SharedFragmentViewModel
import com.example.app.messages.messages


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



        val message = Observer<messages> {



        }

        /*tell our observer to start observing*/
        BluetoothGATT.remoteMessage.observe(viewLifecycleOwner, message)

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