package com.brilliantage.apps.android.tweetr.presentation.base

import activitystarter.ActivityStarter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

/**
 * Created by gosullivan on 11/22/17.
 */
open class BaseActivity(): AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityStarter.fill(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        ActivityStarter.save(this)
    }

    protected fun initToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar!!
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }
}