package com.brilliantage.apps.android.tweetr.repositories.api

import io.reactivex.Flowable
import com.brilliantage.apps.android.tweetr.model.search.SearchResponse
import com.brilliantage.apps.android.tweetr.model.search.token.AccessToken
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by gosullivan on 11/22/17.
 */
interface TwitterApi {


    @GET("1.1/search/tweets.json")
    fun searchTweets(
            @Query("q") query: String): Observable<SearchResponse>


    @FormUrlEncoded
    @POST("oauth2/token")
    fun getToken(
            @Header("Authorization") authorization: String,
            @Field("grant_type") grantType: String
    ): Observable<AccessToken>
}