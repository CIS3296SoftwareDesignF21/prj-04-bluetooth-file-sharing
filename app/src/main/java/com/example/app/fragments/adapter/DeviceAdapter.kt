package com.example.app.fragments.adapter

import android.bluetooth.le.ScanResult
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.DeviceViewHolderBinding
import com.example.app.fragments.data.SharedFragmentViewModel


/**
 * Adapter for displaying remote Bluetooth devices that are being advertised
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class DeviceAdapter(val sharedView: SharedFragmentViewModel) : RecyclerView.Adapter<DeviceAdapter.ScanResultVh>() {

    val TAG = "ScannerAdapter"

    private var itemsList: MutableList<ScanResult> = arrayListOf()

    fun updateView(mutableList:MutableList<ScanResult>){

        val size : Int = itemsList.size

        Log.d(TAG, "updateView: called for itemlist size $size")

        if(mutableList != itemsList){
            itemsList = mutableList
        }
        notifyDataSetChanged()

    }

    override fun getItemCount() = itemsList.size

    private fun getItem(position: Int): ScanResult? = if (itemsList.isEmpty()) null else itemsList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScanResultVh {
        val binding =
            DeviceViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScanResultVh(binding)
    }

    override fun onBindViewHolder(holder: ScanResultVh, position: Int) {
        Log.d(TAG, "onBindViewHolder: called for position $position")
        holder.bind(getItem(position))
    }

    inner class ScanResultVh(private val binding: DeviceViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ScanResult?) {
            item?.let {
                val device = it.device
                binding.deviceName.text = device.name
                binding.deviceAddress.text = device.address
                binding.deviceCard.setOnClickListener{

                    sharedView.setSend(device)

                }
            }
        }

    }


}