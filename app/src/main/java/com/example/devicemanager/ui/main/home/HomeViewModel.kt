package com.example.devicemanager.ui.main.home

import androidx.lifecycle.*
import com.example.devicemanager.data.model.Device
import com.example.devicemanager.data.repo.DeviceRepository
import com.example.devicemanager.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DeviceRepository
) : ViewModel() {

    private var allDevices = listOf<Device>()

    private val _devices = MutableLiveData<Resource<List<Device>>>()
    val devices: LiveData<Resource<List<Device>>> = _devices

    init {
        fetchAllDevices()
    }

    fun fetchAllDevices() {
        _devices.value = Resource.loading(null)

        viewModelScope.launch {
            val response = repository.getAllDevices()
            response.data?.let { allDevices = it }
            _devices.value = response
        }
    }

    fun searchAllDevices(searchKey: String) {
        _devices.value = Resource.loading(null)

        viewModelScope.launch {
            if (searchKey.isEmpty()) {
                _devices.value = Resource.success(allDevices)
            } else {
                val data = allDevices.filter {
                    it.type.contains(searchKey, true) or
                            it.title.contains(searchKey, true)
                }
                _devices.value = Resource.success(data)
            }
        }
    }
}