package com.amin.sample.network

import com.amin.sample.base.BaseResponseShutterStock
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurApi {
    @GET("gallery/{section}/{sort}/{window}/{page}")
    fun getGalley(@Path("section") section: String,
                  @Path("sort") sort: String,
                  @Path("page") page: Int,
                  @Path("window") window: String = "year"
    ): Maybe<BaseResponseShutterStock>
}