package com.brilliantage.apps.android.tweetr.repositories

import com.brilliantage.apps.android.tweetr.model.search.SearchResponse
import com.brilliantage.apps.android.tweetr.repositories.api.TweetrApi
import io.reactivex.Flowable
import com.brilliantage.apps.android.tweetr.repositories.providers.Provider

/**
 * Created by gosullivan on 11/22/17.
 */
interface TweetrRepository {

    fun searchTweets(query:String): Flowable<SearchResponse>

    class TweetrDataRepository : TweetrRepository {

        private val tweetrApi: TweetrApi = TweetrApi()

        override fun searchTweets(query: String): Flowable<SearchResponse> {
            return tweetrApi.searchTweets(query)
        }
    }

    companion object : Provider<TweetrRepository>({ TweetrDataRepository()})

}