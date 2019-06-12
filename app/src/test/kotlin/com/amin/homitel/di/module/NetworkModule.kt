package com.amin.homitel.di.module

import com.amin.homitel.model.Post
import com.amin.homitel.network.PostApi
import com.amin.homitel.utils.ApiUtils
import com.amin.homitel.utils.Constants.BASE_URL
import com.amin.homitel.utils.POST_MOCK_PATH
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
    internal fun providePostApi(retrofit: Retrofit): PostApi {
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