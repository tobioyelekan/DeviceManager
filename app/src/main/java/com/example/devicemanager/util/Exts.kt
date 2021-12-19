package com.example.devicemanager.util

import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

inline fun <reified T> typeToken(): Type = object : TypeToken<T>() {}.type

inline fun <reified T> String.toObject(): T {
    val type = typeToken<T>()
    return Gson().fromJson(this, type)
}

fun ImageView.load(@DrawableRes id: Int) {
    Glide.with(this.context)
        .load(id)
        .into(this)
}

fun Fragment.showMsg(msg: String) {
    Toast.makeText(this.requireContext(), msg, Toast.LENGTH_SHORT).show()
}