package com.amin.sample.base


import com.amin.sample.model.ImgurImage

data class BaseResponseImgur(
    val status: Int,
    val success: Boolean,
    val data: List<ImgurImage>
)