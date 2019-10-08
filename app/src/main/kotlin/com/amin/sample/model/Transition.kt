package com.amin.sample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Transition(
    val model: ImgurImage? = null,
    val image: String,
    val title: String,
    val imageTransition: String? = null,
    val titleTransition: String? = null
) : Parcelable