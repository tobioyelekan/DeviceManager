package com.example.devicemanager.ui.main.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.devicemanager.data.repo.DeviceRepository
import com.example.devicemanager.util.DataSource
import com.example.devicemanager.util.Resource
import com.example.devicemanager.util.MainCoroutineRule
import com.example.devicemanager.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: HomeViewModel

    private val repository = mock(DeviceRepository::class.java)

    @Before
    fun setupViewModel() {
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun `assert that data is fetched`() = mainCoroutineRule.runBlockingTest {
        `when`(repository.getAllDevices()).thenReturn(Resource.success(DataSource.deviceList))

        viewModel.fetchAllDevices()

        verify(repository, atLeastOnce()).getAllDevices()
        assertThat(viewModel.devices.getOrAwaitValue().data, `is`(DataSource.deviceList))
    }

    @Test
    fun `assert that loading shows and data is fetched when successful`() =
        mainCoroutineRule.runBlockingTest {
            `when`(repository.getAllDevices()).thenReturn(Resource.success(DataSource.deviceList))

            mainCoroutineRule.pauseDispatcher()

            viewModel.fetchAllDevices()

            assertThat(viewModel.devices.getOrAwaitValue(), `is`(Resource.loading(null)))

            mainCoroutineRule.resumeDispatcher()

            assertThat(
                viewModel.devices.getOrAwaitValue(),
                `is`(Resource.success(DataSource.deviceList))
            )
        }

    @Test
    fun `assert that error in mock network returns error`() = mainCoroutineRule.runBlockingTest {
        `when`(repository.getAllDevices()).thenReturn(Resource.error(message = "something went wrong"))

        mainCoroutineRule.pauseDispatcher()

        viewModel.fetchAllDevices()

        assertThat(viewModel.devices.getOrAwaitValue(), `is`(Resource.loading(null)))

        mainCoroutineRule.resumeDispatcher()

        assertThat(
            viewModel.devices.getOrAwaitValue(),
            `is`(Resource.error("something went wrong"))
        )
    }

    @Test
    fun `assert that valid search return data`() =
        mainCoroutineRule.runBlockingTest {
            `when`(repository.getAllDevices()).thenReturn(Resource.success(DataSource.deviceList))

            viewModel.fetchAllDevices()

            assertThat(viewModel.devices.getOrAwaitValue().data, `is`(DataSource.deviceList))

            viewModel.searchAllDevices("android")

            assertThat(
                viewModel.devices.getOrAwaitValue().data!![0].type,
                `is`(DataSource.deviceList[0].type)
            )
        }

    @Test
    fun `assert that invalid search returns empty data`() =
        mainCoroutineRule.runBlockingTest {
            `when`(repository.getAllDevices()).thenReturn(Resource.success(DataSource.deviceList))

            viewModel.fetchAllDevices()

            assertThat(viewModel.devices.getOrAwaitValue().data, `is`(DataSource.deviceList))

            viewModel.searchAllDevices("xyz")

            assertThat(
                viewModel.devices.getOrAwaitValue().data, `is`(emptyList())
            )
        }

}