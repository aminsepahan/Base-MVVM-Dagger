package com.amin.sample.base

import androidx.lifecycle.ViewModel
import com.amin.sample.di.componet.DaggerViewModelInjector
import com.amin.sample.di.componet.ViewModelInjector
import com.amin.sample.di.module.NetworkModule
import com.amin.sample.ui.posts.PostsViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostsViewModel -> injector.inject(this)
        }
    }
}