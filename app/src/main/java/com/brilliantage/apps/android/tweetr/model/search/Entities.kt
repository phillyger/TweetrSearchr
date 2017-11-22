package com.brilliantage.apps.android.tweetr.model.search

import com.google.gson.annotations.SerializedName

/**
 * Created by gosullivan on 11/21/17.
 */
class Entities(
        @SerializedName("urls")
        val urls: List<Urls>,

        @SerializedName("hashtags")
        val hashtags: List<Hashtags>,

        @SerializedName("user_mentions")
        val userMentions: List<UserMentions>,

        @SerializedName("symbols")
        val symbols: List<Any>

) 