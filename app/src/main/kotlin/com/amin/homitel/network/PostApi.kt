package com.amin.homitel.network

import com.amin.homitel.model.Post
import io.reactivex.Maybe
import io.reactivex.Observable
import retrofit2.http.GET

interface PostApi {
    @GET("/posts")
    fun getPosts(): Maybe<List<Post>>
}