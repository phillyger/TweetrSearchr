package com.brilliantage.apps.android.tweetr.di.module

import com.brilliantage.apps.android.tweetr.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by gosullivan on 11/21/17.
 */
@Module
class HttpClientModule {

    @Provides
    @Singleton
    fun provideHttpClient(interceptors: Array<out Interceptor>) = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(headersInterceptor())
            .apply { interceptors().addAll(interceptors) }
            .addInterceptor(loggingInterceptor())
            .build()

    fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    fun headersInterceptor() = Interceptor { chain ->
        chain.proceed(chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .addHeader("Content-Type", "application/json")
                .build())
    }



}