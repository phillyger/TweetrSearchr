package com.brilliantage.apps.android.tweetr.presentation.main

import android.util.Base64
import android.util.Log
import com.brilliantage.apps.android.tweetr.BuildConfig
import com.brilliantage.apps.android.tweetr.presentation.base.RxPresenter
import com.brilliantage.apps.android.tweetr.repositories.providers.Pref
import com.brilliantage.apps.android.tweetr.utils.applySchedulers

import com.brilliantage.apps.android.tweetr.utils.smartSubscribe


/**
 * Created by gosullivan on 11/22/17.
 */
open class MainPresenter(val view: MainView) : RxPresenter() {

    private val searchTweetsUseCase by lazy { SearchTweetsUseCase() }

    private val consumerKey:String = BuildConfig.CONSUMER_KEY
    private val consumerSecret:String = BuildConfig.CONSUMER_SECRET

//    private var query:String? = view.query

    override fun onStart() {

        view.searchButtonClickedCallback = { if(Pref.token.isNullOrBlank()) getToken() else searchTweets() }
    }

    private fun searchTweets() {

        Log.d("MainPresenter", "searchTweets")
        subscriptions += searchTweetsUseCase.searchTweets("123'")
                .applySchedulers()
                .smartSubscribe(
//                        onStart = { view.progressVisible = true },
                        onSuccess = { (tweets, metadata) ->
                            view.display(tweets, metadata)
                        },
                        onError = view::showError
//                        onFinish = { view.progressVisible = false }
                )

    }


    private fun getToken() {

        Log.d("MainPresenter", "getToken")

        val authorization = buildTokenAuthorizationString()
        Log.d("MainPresenter", "authorization: " + authorization)
        subscriptions += searchTweetsUseCase.getToken(authorization)
                .applySchedulers()
                .smartSubscribe(
                        onSuccess = { (tokenType, accessToken) ->
                            // write the token to the preferences - don't do this in production app!!
                            Pref.token = accessToken

                            // call searchTweets
                            searchTweets()
                        }
                )
    }

    private fun buildTokenAuthorizationString() : String {

        val inputValue:String = consumerKey+":"+consumerSecret
        Log.d("MainPresenter", "inputValue: " + inputValue)
        val valueEncoded = String(Base64.encode(inputValue.toByteArray(), Base64.DEFAULT))

        return "Basic " + valueEncoded

    }

}