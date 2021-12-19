package com.example.devicemanager.data.source.remote

import com.example.devicemanager.data.model.Device
import com.example.devicemanager.util.Resource

interface DeviceRemoteSource {
    suspend fun getAllDevices(): Resource<List<Device>>
}