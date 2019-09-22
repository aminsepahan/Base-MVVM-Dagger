package com.amin.sample.model

import com.google.gson.annotations.SerializedName


data class ShutterStockImage(
    @SerializedName("aspect")
    val aspect: Double,
    @SerializedName("assets")
    val assets: Assets,
    @SerializedName("contributor")
    val contributor: Contributor,
    @SerializedName("description")
    val description: String,
    @SerializedName("has_model_release")
    val hasModelRelease: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("image_type")
    val imageType: String,
    @SerializedName("media_type")
    val mediaType: String
) {
    data class Assets(
        @SerializedName("huge_thumb")
        val hugeThumb: HugeThumb,
        @SerializedName("large_thumb")
        val largeThumb: LargeThumb,
        @SerializedName("preview")
        val preview: Preview,
        @SerializedName("preview_1000")
        val preview1000: Preview1000,
        @SerializedName("preview_1500")
        val preview1500: Preview1500,
        @SerializedName("small_thumb")
        val smallThumb: SmallThumb
    ) {
        data class HugeThumb(
            @SerializedName("height")
            val height: Int,
            @SerializedName("url")
            val url: String,
            @SerializedName("width")
            val width: Int
        )

        data class LargeThumb(
            @SerializedName("height")
            val height: Int,
            @SerializedName("url")
            val url: String,
            @SerializedName("width")
            val width: Int
        )

        data class Preview(
            @SerializedName("height")
            val height: Int,
            @SerializedName("url")
            val url: String,
            @SerializedName("width")
            val width: Int
        )

        data class Preview1000(
            @SerializedName("height")
            val height: Int,
            @SerializedName("url")
            val url: String,
            @SerializedName("width")
            val width: Int
        )

        data class Preview1500(
            @SerializedName("height")
            val height: Int,
            @SerializedName("url")
            val url: String,
            @SerializedName("width")
            val width: Int
        )

        data class SmallThumb(
            @SerializedName("height")
            val height: Int,
            @SerializedName("url")
            val url: String,
            @SerializedName("width")
            val width: Int
        )
    }

    data class Contributor(
        @SerializedName("id")
        val id: String
    )
}