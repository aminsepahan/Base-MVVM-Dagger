package com.amin.sample.di.componet

import com.amin.sample.di.module.NetworkModule
import com.amin.sample.ui.imgur.ImgurViewModel
import com.amin.sample.ui.shutterStock.ShutterStockViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ImgurViewModelInjector {

    fun inject(viewModel: ImgurViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ImgurViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}