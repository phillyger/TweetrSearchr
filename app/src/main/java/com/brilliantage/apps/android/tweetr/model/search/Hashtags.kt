package com.brilliantage.apps.android.tweetr.model.search

import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by gosullivan on 11/21/17.
 */
@PaperParcel
data class Hashtags(
        @SerializedName("indices")
        val indices: List<Int>,

        @SerializedName("text")
        val text: String
): PaperParcelable {
        companion object {
                @JvmField val CREATOR = PaperParcelHashtags.CREATOR
        }
}