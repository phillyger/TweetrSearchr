package com.brilliantage.apps.android.tweetr.model.search

import com.google.gson.annotations.SerializedName

/**
 * Created by gosullivan on 11/21/17.
 */
data class Response(
        @SerializedName("statuses")
        val statuses: List<Statuses>,

        @SerializedName("search_metadata")
        val searchMetadata: SearchMetadata
)