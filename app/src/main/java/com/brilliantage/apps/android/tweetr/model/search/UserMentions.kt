package com.brilliantage.apps.android.tweetr.model.search

import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by gosullivan on 11/21/17.
 */
@PaperParcel
data class UserMentions(

        @SerializedName("indices")
        val indices: List<Int>,

        @SerializedName("screen_name")
        val screenName: String,

        @SerializedName("id_str")
        val idStr: String,

        @SerializedName("name")
        val name: String,

        @SerializedName("id")
        val id: Long

): PaperParcelable {
        companion object {
                @JvmField val CREATOR = PaperParcelUserMentions.CREATOR
        }
}