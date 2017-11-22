package com.brilliantage.apps.android.tweetr.repositories.api


import com.brilliantage.apps.android.tweetr.model.search.SearchResponse
import retrofit2.Retrofit
import com.brilliantage.apps.android.tweetr.repositories.providers.OAuthRetrofit
import io.reactivex.Flowable

/**
 * Created by gosullivan on 11/22/17.
 */
class TweetrApi {

    private val tweetrApiService:TweetrApiService = makeTweetrApiService()

    private fun makeTweetrApiService() : TweetrApiService {
        val retrofit:Retrofit = OAuthRetrofit.get()
        return retrofit.create(TweetrApiService::class.java);
    }

    fun searchTweets(query:String) : Flowable<SearchResponse> {
        return tweetrApiService.searchTweets(query)
    }

}