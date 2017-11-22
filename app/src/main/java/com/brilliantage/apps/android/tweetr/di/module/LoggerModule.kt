package com.brilliantage.apps.android.tweetr.di.module

import dagger.Module
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

/**
 * Created by gosullivan on 11/21/17.
 */
@Module
class LoggerModule {

    fun provideLogger():HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.d(message)  })
    }
}