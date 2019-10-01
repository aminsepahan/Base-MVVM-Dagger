package com.amin.sample.model

import androidx.annotation.DrawableRes
import com.amin.sample.R
import com.amin.sample.base.App
import com.amin.sample.base.Configs.SampleTypes

data class MainMenuItem(
    val id: Int,
    val title: String,
    val description: String,
    @DrawableRes val imageId: Int
) {
    companion object {
        fun createMenuItem(mode: SampleTypes) = when (mode) {
            SampleTypes.SHUTTER_STOCK -> MainMenuItem(
                0,
                App.res.getString(R.string.shutter_stock_title),
                App.res.getString(R.string.shutter_stock_description),
                R.drawable.img_shutter_stock_logo
            )
            SampleTypes.IMGUR -> MainMenuItem(
                1,
                App.res.getString(R.string.imgur_title),
                App.res.getString(R.string.imgur_description),
                R.drawable.img_imgur_logo
            )
            SampleTypes.BLOG_POSTS -> MainMenuItem(
                2,
                App.res.getString(R.string.posts_title),
                App.res.getString(R.string.posts_description),
                R.drawable.img_jsonplaceholder_logo
            )
            else -> MainMenuItem(-1, "", "", 0)
        }
    }
}