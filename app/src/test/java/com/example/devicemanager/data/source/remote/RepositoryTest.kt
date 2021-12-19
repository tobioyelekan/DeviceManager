package com.example.devicemanager.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.devicemanager.data.repo.DeviceRepositoryImpl
import com.example.devicemanager.util.DataSource
import com.example.devicemanager.util.Resource
import com.example.devicemanager.util.MainCoroutineRule
import org.mockito.Mockito.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.hamcrest.Matchers.`is`
import org.junit.Test

@ExperimentalCoroutinesApi
class RepositoryTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteSource = mock(DeviceRemoteSource::class.java)
    private val repository = DeviceRepositoryImpl(remoteSource)

    @Test
    fun `assert that call to network returns success`() = mainCoroutineRule.runBlockingTest {
        `when`(remoteSource.getAllDevices()).thenReturn(Resource.success(DataSource.deviceList))

        val response = repository.getAllDevices()

        verify(remoteSource, atMostOnce()).getAllDevices()
        assertThat(response, `is`(Resource.success(DataSource.deviceList)))
    }

    @Test
    fun `assert that when error occurs returns success`() = mainCoroutineRule.runBlockingTest {
        `when`(remoteSource.getAllDevices()).thenReturn(Resource.error("error occurred", null))

        val response = repository.getAllDevices()

        verify(remoteSource, atMostOnce()).getAllDevices()
        assertThat(response, `is`(Resource.error("error occurred")))
    }

}