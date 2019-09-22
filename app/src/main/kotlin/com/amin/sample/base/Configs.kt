package com.amin.sample.base

object Configs {
    const val IMAGE_RANDOM_URL = "https://picsum.photos/id/"
    const val BASE_URL = "https://jsonplaceholder.typicode.com"
    val sampleMode = SampleTypes.SHUTTER_STOCK

    const val shutterStockUser = "1edb4-e305d-e4e44-1431c-4ef23-bc039"
    const val shutterStockPass = "5dba4-e8582-212cf-349a2-95329-be873"

    enum class SampleTypes{
        BLOG_POSTS, SHUTTER_STOCK, IMGUR
    }
}