package com.amin.sample.di.componet

import com.amin.sample.di.module.NetworkModule
import com.amin.sample.ui.imgur.ShutterStockViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ShutterStockViewModelInjector {
    /**
     * Injects required dependencies into the specified PostViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(shutterStockViewModel: ShutterStockViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ShutterStockViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}