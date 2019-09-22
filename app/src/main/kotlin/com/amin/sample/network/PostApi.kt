package com.amin.sample.network

import com.amin.sample.model.Post
import io.reactivex.Maybe
import retrofit2.http.GET

interface PostApi {
    @GET("/posts")
    fun getPosts(): Maybe<List<Post>>
}