package com.amin.sample.network

import com.amin.sample.base.BaseResponseShutterStock
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query

interface ShutterStockApi {
    @GET("images/search")
    fun searchImages(@Query("page") page: Int, @Query("query") searchPhrase: String): Maybe<BaseResponseShutterStock>
}