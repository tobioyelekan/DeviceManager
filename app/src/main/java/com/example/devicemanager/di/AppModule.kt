package com.example.devicemanager.di

import com.example.devicemanager.data.repo.DeviceRepository
import com.example.devicemanager.data.repo.DeviceRepositoryImpl
import com.example.devicemanager.data.source.remote.DeviceRemoteSource
import com.example.devicemanager.data.source.remote.DeviceRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun providesRepository(
        deviceRemoteSource: DeviceRemoteSource
    ): DeviceRepository {
        return DeviceRepositoryImpl(deviceRemoteSource)
    }

    @Provides
    @Singleton
    fun providesRemoteSource(): DeviceRemoteSource {
        return DeviceRemoteSourceImpl()
    }
}