package com.brilliantage.apps.android.tweetr.model.search

import com.google.gson.annotations.SerializedName

/**
 * Created by gosullivan on 11/21/17.
 */
data class Urls(
        @SerializedName("display_url")
        val displayUrl: String,

        @SerializedName("indices")
        val indices: List<Int>,

        @SerializedName("expanded_url")
        val expandedUrl: String,

        @SerializedName("url")
        val url: String
)