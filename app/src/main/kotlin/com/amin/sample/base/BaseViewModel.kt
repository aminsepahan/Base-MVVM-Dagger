package com.amin.sample.base

import androidx.lifecycle.ViewModel
import com.amin.sample.di.componet.DaggerImgurViewModelInjector
import com.amin.sample.di.componet.DaggerPostViewModelInjector
import com.amin.sample.di.componet.DaggerShutterStockViewModelInjector
import com.amin.sample.di.module.NetworkModule
import com.amin.sample.ui.imgur.ImgurViewModel
import com.amin.sample.ui.posts.PostsViewModel
import com.amin.sample.ui.shutterStock.ShutterStockViewModel

abstract class BaseViewModel : ViewModel() {
    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is PostsViewModel -> DaggerPostViewModelInjector
                .builder()
                .networkModule(NetworkModule)
                .build().inject(this)
            is ShutterStockViewModel -> DaggerShutterStockViewModelInjector
                .builder()
                .networkModule(NetworkModule)
                .build().inject(this)
            is ImgurViewModel -> DaggerImgurViewModelInjector
                .builder()
                .networkModule(NetworkModule)
                .build().inject(this)
        }
    }
}