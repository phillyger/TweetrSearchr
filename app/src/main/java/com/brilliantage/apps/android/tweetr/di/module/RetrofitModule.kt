package com.brilliantage.apps.android.tweetr.di.module

import com.brilliantage.apps.android.tweetr.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by gosullivan on 11/21/17.
 */
@Module
class RetrofitModule {


    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, converter: Converter.Factory, callAdapter: CallAdapter.Factory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_API_URL)
                .addCallAdapterFactory(callAdapter)
                .addConverterFactory(converter)
                .client(httpClient)
                .build()

    }
}