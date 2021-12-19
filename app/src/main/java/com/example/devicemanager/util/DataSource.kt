package com.example.devicemanager.util

import com.example.devicemanager.R
import com.example.devicemanager.data.model.Device

class DataSource {
    companion object MockData {

        val deviceList = listOf(
            Device(
                1,
                "android",
                "100",
                4.5f,
                true,
                R.drawable.ic_android,
                "Pixel 5",
                "Google phone"
            ),
            Device(
                2,
                "android",
                "150",
                5f,
                true,
                R.drawable.ic_android,
                "Pixel 6 Pro",
                "Best Google phone"
            ),
            Device(
                4,
                "android",
                "100",
                4.8f,
                true,
                R.drawable.ic_android,
                "Pixel 6",
                "Google phone"
            ),
            Device(
                5,
                "ios",
                "300",
                5f,
                true,
                R.drawable.ic_iphone,
                "iPhone 13 Pro",
                "Best Apple phone"
            ),
        )
    }
}