package com.amin.sample.di.componet

import com.amin.sample.di.module.NetworkModule
import com.amin.sample.ui.posts.PostsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface PostViewModelInjector {
    /**
     * Injects required dependencies into the specified PostViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(postListViewModel: PostsViewModel)

    @Component.Builder
    interface Builder {
        fun build(): PostViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}