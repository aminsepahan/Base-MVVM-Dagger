package com.amin.sample.network

import com.amin.sample.base.BaseResponseShutterStock
import com.amin.sample.model.Post
import com.amin.sample.model.ShutterStockImage
import io.reactivex.Maybe
import retrofit2.http.GET

interface ShutterStockApi {
    @GET("/images/search")
    fun searchImages(): Maybe<BaseResponseShutterStock>
}