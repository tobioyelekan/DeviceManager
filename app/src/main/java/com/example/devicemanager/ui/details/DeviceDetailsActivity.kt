package com.example.devicemanager.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.devicemanager.data.model.Device
import com.example.devicemanager.databinding.ActivityDeviceDetailsBinding
import com.example.devicemanager.util.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeviceDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeviceDetailsBinding
    private val viewModel by viewModels<DeviceDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDeviceDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.deviceInfo.observe(this@DeviceDetailsActivity, { device ->
            device?.let { showDetails(it) }
        })
    }

    private fun showDetails(device: Device) {
        with(binding) {
            toolbar.title = device.title
            img.load(device.icon)
            price.text = "$${device.price}"
            rateBar.rating = device.rating
            desc.text = device.desc
            type.text = device.type
        }
    }
}