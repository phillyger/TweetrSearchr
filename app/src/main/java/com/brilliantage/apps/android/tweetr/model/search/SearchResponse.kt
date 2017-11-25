package com.brilliantage.apps.android.tweetr.model.search

import com.google.gson.annotations.SerializedName

/**
 * Created by gosullivan on 11/21/17.
 */
data class SearchResponse(
        @SerializedName("statuses")
        val statusList: List<Status>,

        @SerializedName("search_metadata")
        val searchMetadata: SearchMetadata
)