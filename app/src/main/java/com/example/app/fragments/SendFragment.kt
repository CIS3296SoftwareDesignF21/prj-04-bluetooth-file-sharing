package com.example.app.fragments

import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.app.bluetooth.BluetoothClient
import com.example.app.databinding.FragmentSendBinding
import com.example.app.fragments.data.SharedFragmentViewModel
import com.example.app.messages.messages
import com.example.app.messages.messages_db
import kotlinx.coroutines.runBlocking
import java.io.ByteArrayInputStream



@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SendFragment : Fragment() {

    val sharedViewModel : SharedFragmentViewModel by activityViewModels()

    var fileRequest : Boolean = false


    private lateinit var binding: FragmentSendBinding

    private lateinit var btClient : BluetoothClient

    private lateinit var device : BluetoothDevice

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        val app = requireActivity().application

        btClient = BluetoothClient(app)

        device = sharedViewModel.connection!!.value!!

        val deviceName = device.name
        Log.d("ClientFragment", "device: $deviceName")

        btClient.connectToDevice(device)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.setEmptyMessage()

        binding.imageView.setImageURI(null)

        binding.sendButtonID.setOnClickListener{

            if (fileRequest){

                sharedViewModel.message.value!!.textContent = binding.messageBoxID.text.toString()
                
                sharedViewModel.messagedb.store_sent_message(sharedViewModel.message.value!!)

                btClient.sendMessage(sharedViewModel.message.value!!)
                

            }else{

                val message : messages = messages(
                    binding.messageBoxID.text.toString(),
                    null,
                    "me",
                    "target",
                    0,
                    null,
                    0
                )


                btClient.sendMessage(message)


            }

            sharedViewModel.setHome()

        }

        binding.fileButton.setOnClickListener{

            fileRequest = true

            val intent = Intent()
              .setType("*/*")
              .setAction(Intent.ACTION_OPEN_DOCUMENT)

               activity?.startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)

            binding.imageView.setImageURI(null)

        }
        val result = Observer<messages> { result ->
            /*this calls the recycle view adapter to update our list*/

            if(result.byteArray != null) {
                val inputStream = ByteArrayInputStream(result.byteArray)
                val bitmap = BitmapFactory.decodeStream(inputStream)

                binding.imageView.setImageBitmap(bitmap)
            }

        }

        sharedViewModel.message.observe(viewLifecycleOwner, result)


    }
    companion object{
        fun newInstance(): SendFragment {
            val args = Bundle()
            val fragment = SendFragment()
            fragment.arguments = args
            return fragment
        }
    }

}