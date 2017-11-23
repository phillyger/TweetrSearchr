package com.brilliantage.apps.android.tweetr.repositories.api


import com.brilliantage.apps.android.tweetr.model.search.SearchResponse
import com.brilliantage.apps.android.tweetr.model.search.token.AccessToken
import retrofit2.Retrofit
import com.brilliantage.apps.android.tweetr.repositories.providers.OAuthRetrofit
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by gosullivan on 11/22/17.
 */
class TweetrApi {

    private val tweetrApiService:TweetrApiService = makeTweetrApiService()

    private fun makeTweetrApiService() : TweetrApiService {
        val retrofit:Retrofit = OAuthRetrofit.get()
        return retrofit.create(TweetrApiService::class.java);
    }

    fun searchTweets(query:String) : Observable<SearchResponse> =
            tweetrApiService.searchTweets(query)

    fun getToken(authorization:String, grantType:String) : Observable<AccessToken> =
            tweetrApiService.getToken(authorization, grantType)


}