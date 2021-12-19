package com.example.devicemanager.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.devicemanager.data.model.Device
import com.example.devicemanager.databinding.ItemDeviceBinding
import com.example.devicemanager.util.load

class DeviceAdapter(private val onItemClick: (Device) -> Unit) :
    ListAdapter<Device, DeviceAdapter.ViewHolder>(DeviceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClick(getItem(position))
        }

        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemDeviceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(device: Device) {
            with(binding) {
                img.load(device.icon)
                title.text = device.title
                desc.text = device.desc
            }
        }
    }
}

class DeviceDiffCallback : DiffUtil.ItemCallback<Device>() {
    override fun areItemsTheSame(oldItem: Device, newItem: Device): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Device, newItem: Device): Boolean {
        return oldItem == newItem
    }
}

//class CurrencyAdapter(private val viewModel: CurrencyViewModel) :
//    ListAdapter<CurrencySymbol, CurrencyAdapter.ViewHolder>(CurrencyDiffCallback()) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        return ViewHolder(inflater.inflate(R.layout.currency_item, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(viewModel, getItem(position))
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(viewModel: CurrencyViewModel, symbol: CurrencySymbol) {
//            with(itemView) {
//                currency.text = "${symbol.currency} (${symbol.name})"
//                currencyImg.loadImage(symbol.currency)
//                setOnClickListener { viewModel.selectCurrency(symbol.currency) }
//            }
//        }
//    }
//}

