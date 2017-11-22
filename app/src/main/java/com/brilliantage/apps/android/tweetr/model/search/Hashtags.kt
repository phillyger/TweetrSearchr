package com.brilliantage.apps.android.tweetr.model.search

import com.google.gson.annotations.SerializedName

/**
 * Created by gosullivan on 11/21/17.
 */
data class Hashtags(
        @SerializedName("indices")
        val indices: List<Int>,

        @SerializedName("text")
        val text: String
)