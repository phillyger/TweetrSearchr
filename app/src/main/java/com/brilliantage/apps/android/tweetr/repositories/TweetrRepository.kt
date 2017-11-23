package com.brilliantage.apps.android.tweetr.repositories

import com.brilliantage.apps.android.tweetr.model.search.SearchResponse
import com.brilliantage.apps.android.tweetr.model.search.token.AccessToken
import com.brilliantage.apps.android.tweetr.repositories.api.TweetrService
import com.brilliantage.apps.android.tweetr.repositories.providers.Provider
import io.reactivex.Observable

/**
 * Created by gosullivan on 11/22/17.
 */
interface TweetrRepository {

    fun searchTweets(query:String): Observable<SearchResponse>
    fun getToken(authorization:String): Observable<AccessToken>

    class TweetrDataRepository : TweetrRepository {

        private val grantType = "client_credentials"

        private val tweetrService: TweetrService = TweetrService()


        override fun searchTweets(query: String): Observable<SearchResponse> = tweetrService.searchTweets(query)

        override fun getToken(authorization: String): Observable<AccessToken> = tweetrService.getToken(authorization, grantType)
    }

    companion object : Provider<TweetrRepository>({ TweetrDataRepository()})

}