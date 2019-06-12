package com.amin.homitel.base

import androidx.lifecycle.ViewModel
import com.amin.homitel.di.componet.DaggerViewModelInjector
import com.amin.homitel.di.componet.ViewModelInjector
import com.amin.homitel.di.module.NetworkModule
import com.amin.homitel.ui.posts.PostsViewModel

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