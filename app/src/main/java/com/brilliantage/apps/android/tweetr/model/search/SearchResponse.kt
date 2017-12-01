package com.brilliantage.apps.android.tweetr.model.search

import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by gosullivan on 11/21/17.
 */
@PaperParcel
data class SearchResponse(
        @SerializedName("statuses")
        val statusList: List<Status>,

        @SerializedName("search_metadata")
        val searchMetadata: SearchMetadata
): PaperParcelable {

        companion object {
                @JvmField val CREATOR = PaperParcelSearchResponse.CREATOR
        }
}