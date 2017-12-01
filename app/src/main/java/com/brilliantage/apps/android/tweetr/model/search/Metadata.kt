package com.brilliantage.apps.android.tweetr.model.search

import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by gosullivan on 11/21/17.
 */
@PaperParcel
data class Metadata(

        @SerializedName("result_type")
        val resultType: String,

        @SerializedName("iso_language_code")
        val isoLanguageCode: String
): PaperParcelable {
        companion object {
                @JvmField val CREATOR = PaperParcelMetadata.CREATOR
        }
}