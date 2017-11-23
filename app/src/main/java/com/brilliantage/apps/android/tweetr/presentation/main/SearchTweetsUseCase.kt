package com.brilliantage.apps.android.tweetr.presentation.main

import com.brilliantage.apps.android.tweetr.repositories.TweetrRepository

/**
 * Created by gosullivan on 11/22/17.
 */
class SearchTweetsUseCase {

    val tweetRepository by TweetrRepository.lazyGet()

    fun searchTweets(query:String) = tweetRepository.searchTweets(query)

    fun getToken(authorization:String) = tweetRepository.getToken(authorization)


}