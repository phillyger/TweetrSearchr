package com.brilliantage.apps.android.tweetr.repositories.api

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import com.brilliantage.apps.android.tweetr.model.search.SearchResponse

/**
 * Created by gosullivan on 11/22/17.
 */
interface TweetrApiService {

    @GET("search/tweets.json")
    fun searchTweets(@Query("q") query: String): Flowable<SearchResponse>

}