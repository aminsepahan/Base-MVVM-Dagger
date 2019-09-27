package com.amin.sample.model

import android.widget.ImageView
import android.widget.TextView

data class Transition<T>(
    val model: T,
    val imageView: ImageView? = null,
    val title: TextView? = null
)