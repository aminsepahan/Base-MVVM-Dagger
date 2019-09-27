package com.amin.sample.di.module

import com.amin.sample.base.Configs
import com.amin.sample.base.Configs.BASE_URL
import com.amin.sample.base.Configs.sampleMode
import com.amin.sample.network.PostApi
import com.amin.sample.network.ShutterStockApi
import com.amin.sample.utils.ioThread
import dagger.Module
import dagger.Provides
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Singleton
    internal fun providePostApi(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideShutterStockApi(retrofit: Retrofit): ShutterStockApi {
        return retrofit.create(ShutterStockApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Singleton
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createHttpClient().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(ioThread()))
            .build()
    }

    @Provides
    @Singleton
    fun createHttpClient(): OkHttpClient.Builder {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        okHttpClientBuilder.addInterceptor { chain ->
            val request = chain.request().newBuilder()
            when (sampleMode) {
                Configs.SampleTypes.SHUTTER_STOCK -> request.addHeader(
                    "Authorization",
                    Credentials.basic(Configs.shutterStockUser, Configs.shutterStockPass)
                )
                else -> {
                }
            }
            chain.proceed(request.build())
        }
        return okHttpClientBuilder
    }
}