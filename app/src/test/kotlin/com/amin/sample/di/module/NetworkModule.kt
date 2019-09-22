package com.amin.sample.di.module

import com.amin.sample.base.Configs.BASE_URL
import com.amin.sample.model.Post
import com.amin.sample.network.PostApi
import com.amin.sample.utils.ApiUtils
import com.amin.sample.utils.POST_MOCK_PATH
import dagger.Module
import dagger.Provides
import io.reactivex.Maybe
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    @Provides
    @Singleton
    internal fun providePostApi(@Suppress("UNUSED_PARAMETER") retrofit: Retrofit): PostApi {
        return object : PostApi {
            override fun getPosts(): Maybe<List<Post>> {
                return Maybe.fromCallable { ApiUtils.getUrl<List<Post>>(POST_MOCK_PATH) }
            }
        }
    }

    @Provides
    @Singleton
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).build()
    }
}