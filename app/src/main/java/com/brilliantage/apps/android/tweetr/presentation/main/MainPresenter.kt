package com.brilliantage.apps.android.tweetr.presentation.main

import android.util.Base64
import android.util.Log
import com.brilliantage.apps.android.tweetr.BuildConfig
import com.brilliantage.apps.android.tweetr.model.search.Metadata
import com.brilliantage.apps.android.tweetr.model.search.SearchMetadata
import com.brilliantage.apps.android.tweetr.presentation.base.RxPresenter
import com.brilliantage.apps.android.tweetr.repositories.providers.Pref
import com.brilliantage.apps.android.tweetr.utils.applySchedulers
import com.brilliantage.apps.android.tweetr.model.search.Status

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

    private var statusList:ArrayList<Status> = ArrayList<Status>()
    private var metaData: SearchMetadata? = null


    var isLoadingData:Boolean = false

//    private var query:String? = view.query

    override fun onStart() {

//        view.searchButtonClickedCallback = { if(isAccessTokenAvailable) searchTweets() else getToken()}

        if(isAccessTokenAvailable) searchTweets("%23r.e.m'") else getToken()
    }

    private fun searchTweets(query:String) {

        isLoadingData = true
        Log.d("MainPresenter", "searchTweets")
        subscriptions += searchTweetsUseCase.searchTweets(query)
                .applySchedulers()
                .smartSubscribe(
                        onStart = { Log.d(TAG , "onStart") },
                        onSuccess = { (searchStatuses, searchMetadata) ->

                            Log.d(TAG, "metadata nextresults:" + searchMetadata.nextResults)
                            setStatusList(searchStatuses)
                            setMetadata(searchMetadata)
                            // add new results to list

//                            Log.d(TAG , "tweet count is ${statusList.size}")
//                            Log.d(TAG , "next results are ${metadata.nextResults}")


                        },
                        onError = view::showError,
                        onFinish = { // send results back to the view object
                            isLoadingData = false
                            view.updateUI()
                             }
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
                                searchTweets("%23u2'")
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

    // status list methods
    fun getStatusListCount():Int {
        return statusList.size
    }

    private fun setStatusList(list: List<Status>) {
        statusList.addAll(list)
    }

    fun getStatusList():List<Status> = statusList

    // meta data
    private fun setMetadata(searchMetadata: SearchMetadata) {
        metaData = searchMetadata
    }

    fun getMetadata():SearchMetadata = metaData!!

    fun loadMoreTweets() {

        metaData?.let {
            Log.d(TAG, "nextResults: " + it.nextResults)
            if (!it.nextResults.isNullOrBlank()) {
                val query =  it.nextResults.substring(startIndex = 1)
                Log.d(TAG, "query: " + it.nextResults.substring(startIndex = 1))
                searchTweets(query)
            }
        }

    }

}