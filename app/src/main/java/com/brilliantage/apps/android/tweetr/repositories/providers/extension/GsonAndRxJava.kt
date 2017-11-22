package com.brilliantage.apps.android.tweetr.repositories.providers.extension

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Created by gosullivan on 11/21/17.
 */

fun Retrofit.Builder.addCallAdapters():Retrofit.Builder = this
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())