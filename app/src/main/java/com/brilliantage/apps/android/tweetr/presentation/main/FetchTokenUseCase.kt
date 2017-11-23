package com.brilliantage.apps.android.tweetr.presentation.main

import com.brilliantage.apps.android.tweetr.repositories.TweetrRepository

/**
 * Created by gosullivan on 11/23/17.
 */
class FetchTokenUseCase {

    val tweetRepository by TweetrRepository.lazyGet()

    fun getToken(authorization:String) = tweetRepository.getToken(authorization)

}