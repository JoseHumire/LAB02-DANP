package com.example.danp

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

const val myColorPrimary = "#1b1c1b"
const val myColorBackground = "#f6f5f9"
const val myColorSecond = "#285667"
