package com.brilliantage.apps.android.tweetr.repositories.providers.extension

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
/**
 * Created by gosullivan on 11/21/17.
 */

// add extension method for Call Adapters
fun Retrofit.Builder.addCallAdapters():Retrofit.Builder = this
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

// add extension method for Cconverters
fun Retrofit.Builder.addConverters(): Retrofit.Builder = this
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
