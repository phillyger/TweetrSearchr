package com.brilliantage.apps.android.tweetr.model.search

import com.google.gson.annotations.SerializedName

/**
 * Created by gosullivan on 11/21/17.
 */
data class Status(

        @SerializedName("metadata")
        val metadata: Metadata,

        @SerializedName("created_at")
        val createdAt: String,

        @SerializedName("source")
        val source: String,

        @SerializedName("retweet_count")
        val retweetCount: Int,

        @SerializedName("retweeted")
        val retweeted: Boolean,


        @SerializedName("is_quote_status")
        val isQuoteStatus: Boolean,

        @SerializedName("id_str")
        val idStr: String,

        @SerializedName("favorite_count")
        val favoriteCount: Int,

        @SerializedName("id")
        val id: Long,

        @SerializedName("text")
        val text: String,

        @SerializedName("lang")
        val lang: String,

        @SerializedName("favorited")
        val favorited: Boolean,

        @SerializedName("possibly_sensitive")
        val possiblySensitive: Boolean,

        @SerializedName("truncated")
        val truncated: Boolean,

        @SerializedName("entities")
        val entities: Entities,

        @SerializedName("user")
        val user: User

)