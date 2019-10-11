package com.amin.sample.model

import com.amin.sample.base.Configs

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) {
    fun getPostImage(): String {
        return Configs.IMAGE_RANDOM_URL + id + "/250"
    }
}