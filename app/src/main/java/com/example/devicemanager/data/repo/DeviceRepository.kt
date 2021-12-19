package com.example.devicemanager.data.repo

import com.example.devicemanager.data.model.Device
import com.example.devicemanager.util.Resource

interface DeviceRepository {
    suspend fun getAllDevices(): Resource<List<Device>>
}