package com.example.app.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.app.bluetooth.BluetoothGATT
import com.example.app.databinding.FragmentSendBinding
import com.example.app.fragments.data.SharedFragmentViewModel

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SendFragment : Fragment() {

    val sharedViewModel : SharedFragmentViewModel by activityViewModels()


    private lateinit var binding: FragmentSendBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sendButtonID.setOnClickListener{

            BluetoothGATT.postMessage(binding.messageBoxID.text.toString())
            sharedViewModel.setHome()

        }

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