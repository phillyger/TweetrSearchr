package com.brilliantage.apps.android.tweetr.model.search

import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by gosullivan on 11/21/17.
 */
@PaperParcel
data class SearchMetadata(@SerializedName("max_id_str")
                          val maxIdStr: String,

                          @SerializedName("next_results")
                          val nextResults: String,

                          @SerializedName("since_id_str")
                          val sinceIdStr: String,

                          @SerializedName("query")
                          val query: String,

                          @SerializedName("count")
                          val count: Int,

                          @SerializedName("max_id")
                          val maxId: Long,

                          @SerializedName("since_id")
                          val sinceId: Int,

                          @SerializedName("completed_in")
                          val completedIn: Double,

                          @SerializedName("refresh_url")
                          val refreshUrl: String) : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelSearchMetadata.CREATOR
    }
}