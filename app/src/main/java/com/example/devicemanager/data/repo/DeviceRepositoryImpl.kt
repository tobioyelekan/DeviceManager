package com.example.devicemanager.data.repo

import com.example.devicemanager.data.model.Device
import com.example.devicemanager.data.source.remote.DeviceRemoteSource
import com.example.devicemanager.util.Resource
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val remoteSource: DeviceRemoteSource,
) : DeviceRepository {

    override suspend fun getAllDevices(): Resource<List<Device>> {
        return remoteSource.getAllDevices()
    }

}