package com.brilliantage.apps.android.tweetr.repositories.providers

import com.brilliantage.apps.android.tweetr.BuildConfig
import com.brilliantage.apps.android.tweetr.repositories.providers.extension.addCallAdapters
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by gosullivan on 11/21/17.
 */
object UnauthenticatedApi : Provider<Retrofit>({
    makeRetrofit()
})

object OAuthRetrofit : Provider<Retrofit>({
    makeRetrofit(accessTokenProvidingInterceptor())
})

fun makeRetrofit(vararg interceptors: Interceptor) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API_URL)
        .client(makeHttpClient(interceptors))
        .addCallAdapters()
        .build()

private fun makeHttpClient(interceptors: Array<out Interceptor>) = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(headersInterceptor())
        .apply { interceptors().addAll(interceptors) }
        .addInterceptor(loggingInterceptor())
        .build()

fun accessTokenProvidingInterceptor() = Interceptor { chain ->
    val accessToken = Pref.token
    chain.proceed(chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build())
}

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