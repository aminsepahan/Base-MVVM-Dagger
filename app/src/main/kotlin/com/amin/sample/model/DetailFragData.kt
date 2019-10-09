package com.amin.sample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailFragData(
    val model: ImgurImage? = null,
    val imageUrl: String,
    val title: String,
    val imageTransition: String,
    val titleTransition: String?
) : Parcelable