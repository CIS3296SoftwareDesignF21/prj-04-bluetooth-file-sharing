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

package com.example.app.fragments.adapter

import android.bluetooth.le.ScanResult
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.MessageViewHolderBinding
import com.example.app.fragments.data.SharedFragmentViewModel
import com.example.app.messages.messages


/**
 * Adapter for displaying remote Bluetooth devices that are being advertised
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MessageAdapter(val sharedView: SharedFragmentViewModel) : RecyclerView.Adapter<MessageAdapter.ScanResultVh>() {

    val TAG = "ScannerAdapter"

    private var itemsList: MutableList<messages> = arrayListOf()

    fun updateView(mutableList:MutableList<messages>){

        val size : Int = itemsList.size


        if(mutableList != itemsList){
            itemsList = mutableList
        }
        notifyDataSetChanged()

    }

    override fun getItemCount() = itemsList.size

    private fun getItem(position: Int): messages? = if (itemsList.isEmpty()) null else itemsList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScanResultVh {
        val binding =
            MessageViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScanResultVh(binding)
    }

    override fun onBindViewHolder(holder: ScanResultVh, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ScanResultVh(private val binding: MessageViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: messages?) {
            item?.let {
                val message = it

                if(it.byteArray == null){
                    binding.deviceName.text = "Text Message"
                } else {
                    binding.deviceName.text = "File"
                }

                binding.deviceCard.setOnClickListener{

                    sharedView.setClient(message)

                }
            }
        }

    }


}
