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

    private val TAG = MainPresenter::class.java.name
    private val searchTweetsUseCase by lazy { SearchTweetsUseCase() }

    private val fetchTokenUseCase by lazy { FetchTokenUseCase() }

    private val consumerKey:String = BuildConfig.CONSUMER_KEY
    private val consumerSecret:String = BuildConfig.CONSUMER_SECRET

    private val isAccessTokenAvailable : Boolean
        get() = !Pref.token.isNullOrBlank()

//    private var query:String? = view.query

    override fun onStart() {

        view.searchButtonClickedCallback = { if(isAccessTokenAvailable) searchTweets() else getToken()}
    }

    private fun searchTweets() {

        Log.d("MainPresenter", "searchTweets")
        subscriptions += searchTweetsUseCase.searchTweets("%23u2'")
                .applySchedulers()
                .smartSubscribe(
                        onStart = { Log.d(TAG , "onStart") },
                        onSuccess = { (tweets, metadata) ->

                            Log.d(TAG , "tweet count is ${tweets.size}")
                            Log.d(TAG , "next results are ${metadata.nextResults}")
//                            view.display(tweets, metadata)
                        },
                        onError = view::showError,
                        onFinish = { Log.d(TAG , "onFinish") }
                )

    }


    private fun getToken() {

        Log.d("MainPresenter", "getToken")

        val authorization = buildTokenAuthorizationString()
        Log.d("MainPresenter", "authorization: " + authorization)
        subscriptions += fetchTokenUseCase.getToken(authorization)
                .applySchedulers()
                .smartSubscribe(
                        onStart = { Log.d(TAG , "onStart") },
                        onSuccess = { (tokenType, accessToken) ->
                            // write the token to the preferences - don't do this in production app!!

                            Log.d(TAG , "tokenType is $tokenType")
                            Log.d(TAG , "accessToken is $accessToken")
                            Pref.token = accessToken


                        },
                        onError = view::showError,
                        onFinish = {
                            Log.d(TAG , "onFinish")
                            if (isAccessTokenAvailable) {
                                // call searchTweets
                                searchTweets()
                            }
                        }

                )
    }

    private fun buildTokenAuthorizationString() : String {

        val inputValue:String = consumerKey+":"+consumerSecret
        Log.d("MainPresenter", "inputValue: " + inputValue)
        val valueEncoded = String(Base64.encode(inputValue.toByteArray(), Base64.NO_WRAP))

        return "Basic " + valueEncoded

    }





}