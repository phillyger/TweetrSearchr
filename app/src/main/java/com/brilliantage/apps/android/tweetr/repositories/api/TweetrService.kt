package com.brilliantage.apps.android.tweetr.repositories.api


import com.brilliantage.apps.android.tweetr.model.search.SearchResponse
import com.brilliantage.apps.android.tweetr.model.search.token.AccessToken
import retrofit2.Retrofit
import com.brilliantage.apps.android.tweetr.repositories.providers.OAuthRetrofit
import com.brilliantage.apps.android.tweetr.repositories.providers.UnauthenticatedRetrofit
import io.reactivex.Observable

/**
 * Created by gosullivan on 11/22/17.
 */
class TweetrService {

    private val twitterApi: TwitterApi = makeTweetrOauthService()

    private fun makeTweetrOauthService() : TwitterApi {
        val retrofit:Retrofit = OAuthRetrofit.get()
        return retrofit.create(TwitterApi::class.java);
    }

    private fun makeTweetrUnauthenticatedService() : TwitterApi {
        val retrofit:Retrofit = UnauthenticatedRetrofit.get()
        return retrofit.create(TwitterApi::class.java);
    }

    fun searchTweets(query:String) : Observable<SearchResponse> =
            twitterApi.searchTweets(query)

    fun getToken(authorization:String, grantType:String) : Observable<AccessToken> =
            twitterApi.getToken(authorization, grantType)


}