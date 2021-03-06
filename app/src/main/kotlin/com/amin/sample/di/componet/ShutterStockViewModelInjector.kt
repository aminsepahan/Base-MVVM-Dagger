package com.amin.sample.di.componet

import com.amin.sample.di.module.NetworkModule
import com.amin.sample.ui.shutterStock.ShutterStockViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ShutterStockViewModelInjector {

    fun inject(shutterStockViewModel: ShutterStockViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ShutterStockViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}