package com.example.devicemanager.data.source.remote

import com.example.devicemanager.data.model.Device
import com.example.devicemanager.util.DataSource
import com.example.devicemanager.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class DeviceRemoteSourceImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DeviceRemoteSource {

    override suspend fun getAllDevices(): Resource<List<Device>> =
        withContext(ioDispatcher) {
            return@withContext try {
                delay(1000)
                Resource.success(DataSource.deviceList)
            } catch (e: Exception) {
                Resource.error(e.message ?: e.localizedMessage ?: "something went wrong")
            }
        }
}