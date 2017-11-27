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
import java.util.*
import java.io.UnsupportedEncodingException
import java.net.URLDecoder




/**
 * Created by gosullivan on 11/22/17.
 *
 */
open class MainPresenter(val view: MainView) : RxPresenter() {

    private val TAG = MainPresenter::class.java.simpleName

    private val searchTweetsUseCase by lazy { SearchTweetsUseCase() }

    private val fetchTokenUseCase by lazy { FetchTokenUseCase() }

    private val consumerKey:String = BuildConfig.CONSUMER_KEY
    private val consumerSecret:String = BuildConfig.CONSUMER_SECRET

    private val isAccessTokenAvailable : Boolean
        get() = !Pref.token.isNullOrBlank()

    private var statusList:ArrayList<Status> = ArrayList<Status>()
    private var metaData: SearchMetadata? = null


    override fun onStart() {



        if (!isAccessTokenAvailable) getToken()
    }

    /**
     * Performs a search for tweets from Twitter, passing in a query string.
     *
     * @see searchTweetsUseCase
     */
     fun searchTweets(query:String, maxId:String?, includeEntities:Boolean?) {


        Log.d("MainPresenter", "searchTweets")
        subscriptions += searchTweetsUseCase.searchTweets(query, maxId, includeEntities)
                .applySchedulers()
                .smartSubscribe(
                        onStart = { },
                        onSuccess = { (searchStatuses, searchMetadata) ->

                            // set start position for adapter dataset
                            val positionStart:Int = statusList.size +1

                            // set the respective data field objects in presenter.
                            setStatusList(searchStatuses)
                            setMetadata(searchMetadata)

//                            Log.d(TAG, "metadata nextresults:" + searchMetadata.nextResults)
//                            Log.d(TAG , "tweet count is ${statusList.size}")
//                            Log.d(TAG , "next results are ${metadata.nextResults}")

                            // add new results to list
                            val itemCount:Int = searchStatuses.size

                            // only insert if items have been returned.
                            if (itemCount > 0)
                                view.updateUI(positionStart, itemCount)

                        },
                        onError = view::showError,
                        onFinish = {}
                )

    }


    /**
     * Performs a call to fetch a valid oath token from Twitter.
     * The access token is then saved to the user prefs.
     *
     * @see buildTokenAuthorizationString
     * @see fetchTokenUseCase
     */
    private fun getToken() {

        // fetch the authorization string
        val authorization = buildTokenAuthorizationString()

        // make call to get token
        subscriptions += fetchTokenUseCase.getToken(authorization)
                .applySchedulers()
                .smartSubscribe(
                        onStart = { },
                        onSuccess = { (tokenType, accessToken) ->
                            // write the token to the preferences - don't do this in production app!!

//                            Log.d(TAG , "tokenType is $tokenType")
//                            Log.d(TAG , "accessToken is $accessToken")
                            Pref.token = accessToken


                        },
                        onError = view::showError,
                        onFinish = { }

                )
    }

    /**
     * Builds a token authorization string used in the call to getToken
     *
     * @see getToken
     */
    private fun buildTokenAuthorizationString() : String {

        val inputValue:String = consumerKey+":"+consumerSecret
//        Log.d("MainPresenter", "inputValue: " + inputValue)
        val valueEncoded = String(Base64.encode(inputValue.toByteArray(), Base64.NO_WRAP))

        return "Basic " + valueEncoded

    }

    /**
     * returns a count of the objects in the status list (aka tweet count)
     */
    fun getStatusListCount():Int {
        return statusList.size
    }

    /**
     * setter for the status list
     */
    private fun setStatusList(list: List<Status>) {
        statusList.addAll(list)
    }

    /**
     * getter for the status list
     */
    fun getStatusList():List<Status> = statusList

    /**
     * setter for the meta data object
     */
    private fun setMetadata(searchMetadata: SearchMetadata) {
        metaData = searchMetadata
    }


    /**
     * Loads the next set of available tweets.
     * Needed to split the nextResults key/value in map since the query (i.e. q) value was already
     * included nextResults and conflicted with how Retrofit expected the value.
     *
     */
    fun loadMoreTweets() {

        val QUERY_PARAM_Q = "q"
        val QUERY_PARAM_MAX_ID = "max_id"
//        val QUERY_PARAM_SINCE_ID = "since_id"


        metaData?.let {
            if (!it.nextResults.isNullOrBlank()) {
                val query =  it.nextResults.substring(startIndex = 1)

                val splitQuery = splitQuery(query)
                val q = (splitQuery.get(QUERY_PARAM_Q) as? String) ?: ""
                val maxId = (splitQuery.get(QUERY_PARAM_MAX_ID) as? String) ?: ""

                searchTweets(q, maxId, true)
            }
        }

    }


    /**
     * splits a query string into a map of key value pairs.
     */
    @Throws(UnsupportedEncodingException::class)
    fun splitQuery(query:String): Map<String, String> {
        val query_pairs = LinkedHashMap<String, String>()
        val pairs = query.split("&".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        for (pair in pairs) {
            val idx = pair.indexOf("=")
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"))
        }
        return query_pairs
    }


    /**
     * resets the search objects.
     */
    fun resetSearch() {
        statusList.clear()
        metaData = null
    }
}