package com.brilliantage.apps.android.tweetr.model.search

import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by gosullivan on 11/21/17.
 */
@PaperParcel
data class Entities(
        @SerializedName("urls")
        val urls: List<Urls>,

        @SerializedName("hashtags")
        val hashtags: List<Hashtags>,

        @SerializedName("user_mentions")
        val userMentions: List<UserMentions>

): PaperParcelable {
        companion object {
                @JvmField val CREATOR = PaperParcelEntities.CREATOR
        }
}