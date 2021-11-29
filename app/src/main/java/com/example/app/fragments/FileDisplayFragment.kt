package com.example.app.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.app.R
import com.example.app.messages.messages
import java.io.File

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val FILE_TYPE = "TYPE"
private const val FILE_SENDER = "SENDER"
private const val FILE_TIME = "TIME"

class FileDisplayFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private lateinit var message : messages
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(FILE_TYPE)
            param2 = it.getString(FILE_SENDER)
            param3 = it.getString(FILE_TIME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_file_display, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tv : TextView = view.findViewById(R.id.file_display_tv)
        var text_for_tv = "Sender: $param1\nTime Received: $param3\nFile Type: ${param2.toString()}"
        tv.text = text_for_tv
    }
    companion object {
        fun newInstance(f : messages) =
            FileDisplayFragment().apply {
                arguments = Bundle().apply {
                    putString(FILE_TYPE, f.FileType)
                    putString(FILE_SENDER, f.sender)
                    putInt(FILE_TIME, f.timeReceived)
                }
            }
    }
}