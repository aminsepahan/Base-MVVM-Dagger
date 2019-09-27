package com.amin.sample.base


import com.amin.sample.model.ShutterStockImage
import com.google.gson.annotations.SerializedName

data class BaseResponseShutterStock(
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("search_id")
    val searchId: String,
    @SerializedName("total_count")
    val totalCount: Int,
    val data: List<ShutterStockImage>
)