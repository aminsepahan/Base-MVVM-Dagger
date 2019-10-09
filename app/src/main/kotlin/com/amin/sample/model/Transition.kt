package com.amin.sample.model

import android.widget.ImageView
import android.widget.TextView

data class Transition(
    val model: ImgurImage? = null,
    val imageUrl: String,
    val title: String,
    val imageTransition: ImageView,
    val titleTransition: TextView? = null
)