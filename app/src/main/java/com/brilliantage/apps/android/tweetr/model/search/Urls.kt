package com.brilliantage.apps.android.tweetr.model.search

import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by gosullivan on 11/21/17.
 */
@PaperParcel
data class Urls(
        @SerializedName("display_url")
        val displayUrl: String,

        @SerializedName("indices")
        val indices: List<Int>,

        @SerializedName("expanded_url")
        val expandedUrl: String,

        @SerializedName("url")
        val url: String
): PaperParcelable {
        companion object {
                @JvmField val CREATOR = PaperParcelUrls.CREATOR
        }
}