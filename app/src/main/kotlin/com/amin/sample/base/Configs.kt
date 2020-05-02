package com.amin.sample.base

import com.amin.sample.utils.EnumCompanion

object Configs {
    const val IMAGE_RANDOM_URL = "https://picsum.photos/id/"
    const val LOG_TAG = "AMMMIN DEBUG"
    var sampleMode = SampleTypes.SHUTTER_STOCK

    fun getBaseURL() = when (sampleMode) {
        SampleTypes.BLOG_POSTS -> "https://jsonplaceholder.typicode.com/"
        SampleTypes.SHUTTER_STOCK -> "https://api.shutterstock.com/v2/"
        SampleTypes.IMGUR -> "https://api.imgur.com/3/"
        else -> "https://jsonplaceholder.typicode.com/"
    }

    const val shutterStockUser = "1edb4-e305d-e4e44-1431c-4ef23-bc039"
    const val shutterStockPass = "5dba4-e8582-212cf-349a2-95329-be873"

    val effectiveSize = 4

    enum class SampleTypes(val value: Int) {
        SHUTTER_STOCK(0),
        IMGUR(1),
        BLOG_POSTS(2),
        WAV_DECODE(3),
        EMPTY4(4),
        EMPTY5(5),
        EMPTY6(6),
        EMPTY7(7);

        companion object : EnumCompanion<Int, SampleTypes>(
            values().associateBy(SampleTypes::value)
        )
    }
}