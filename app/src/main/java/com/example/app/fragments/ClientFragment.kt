package com.example.app.fragments

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.app.bluetooth.BluetoothGATT
import com.example.app.databinding.FragmentClientBinding
import com.example.app.fragments.data.SharedFragmentViewModel
import com.example.app.messages.messages
import java.io.ByteArrayInputStream


/*
*  This fragment creates a connection to the selected device and updates the UI whenever data is updated on the
*  connection. First we create the connection with bluetooth client. Then the client updates the connectionlivedata
*  which in turn trigger the UI to update with the new data to display to the user.
*
*/
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ClientFragment : Fragment() {

    private lateinit var binding: FragmentClientBinding
    val sharedViewModel : SharedFragmentViewModel by activityViewModels()


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


        val it = sharedViewModel.displayMessage.value!!

        if(it.byteArray!=null) {
            val inputStream = ByteArrayInputStream(it.byteArray)
            val bitmap = BitmapFactory.decodeStream(inputStream)

            binding.imageView.setImageBitmap(bitmap)
        }


        binding.textView.text = it.textContent
        binding.deviceTitle.text = it.sender


        /*tell our observer to start observing*/

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