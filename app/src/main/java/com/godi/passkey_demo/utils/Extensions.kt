package com.godi.passkey_demo.utils

import android.content.Context

fun Context.readFromAsset(fileName: String): String {
    var data = ""
    this.assets.open(fileName).bufferedReader().use {
        data = it.readText()
    }
    return data
}