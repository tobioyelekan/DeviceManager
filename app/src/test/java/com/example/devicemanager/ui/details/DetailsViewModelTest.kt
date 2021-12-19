package com.example.devicemanager.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.devicemanager.util.DataSource
import com.example.devicemanager.util.MainCoroutineRule
import com.example.devicemanager.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.hamcrest.core.Is.`is`
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val stateHandle = SavedStateHandle(mapOf("device" to DataSource.deviceList[0]))
    private val viewModel = DeviceDetailsViewModel(stateHandle)

    @Test
    fun checkPassedData() {
        val deviceInfo = viewModel.deviceInfo.getOrAwaitValue()
        assertThat(deviceInfo, notNullValue())
        assertThat(deviceInfo, `is`(DataSource.deviceList[0]))
    }

}