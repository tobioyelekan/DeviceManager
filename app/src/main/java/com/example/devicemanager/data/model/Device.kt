package com.example.devicemanager.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Device(
    val id: Int,
    val type: String,
    val price: String,
    val rating: Float,
    val isFavorite: Boolean,
    val icon: Int,
    val title: String,
    val desc: String
) : Parcelable