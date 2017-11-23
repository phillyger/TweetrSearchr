package com.brilliantage.apps.android.tweetr.repositories.api


import com.brilliantage.apps.android.tweetr.model.search.SearchResponse
import com.brilliantage.apps.android.tweetr.model.search.token.AccessToken
import retrofit2.Retrofit
import com.brilliantage.apps.android.tweetr.repositories.providers.OAuthRetrofit
import io.reactivex.Observable

/**
 * Created by gosullivan on 11/22/17.
 */
class TwitterApi {

    private val tweetrService: TweetrService = makeTweetrApiService()

    private fun makeTweetrApiService() : TweetrService {
        val retrofit:Retrofit = OAuthRetrofit.get()
        return retrofit.create(TweetrService::class.java);
    }

    fun searchTweets(query:String) : Observable<SearchResponse> =
            tweetrService.searchTweets(query)

    fun getToken(authorization:String, grantType:String) : Observable<AccessToken> =
            tweetrService.getToken(authorization, grantType)


}