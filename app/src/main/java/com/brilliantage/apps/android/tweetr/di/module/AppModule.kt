package com.brilliantage.apps.android.tweetr.di.module

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by gosullivan on 11/21/17.
 */
class AppModule(val application: Application) {


    @Provides
    @Singleton
    fun providesApplication() = application

    @Provides
    @Singleton
    fun providesSharedPreferences(application: Application):SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

}