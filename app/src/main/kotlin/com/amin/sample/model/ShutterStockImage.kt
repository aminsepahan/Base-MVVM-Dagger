package com.amin.sample.model

import com.google.gson.annotations.SerializedName


data class ShutterStockImage(
    val aspect: Double,
    val assets: Assets,
    val description: String,
    val id: String
) {
    data class Assets(
        @SerializedName("huge_thumb")
        val hugeThumb: Thumb,
        @SerializedName("large_thumb")
        val largeThumb: Thumb,
        val preview: Thumb,
        @SerializedName("small_thumb")
        val smallThumb: Thumb
    ) {
        data class Thumb(
            val height: Int,
            val url: String,
            val width: Int
        )
    }
}