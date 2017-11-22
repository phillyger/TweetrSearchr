package com.brilliantage.apps.android.tweetr.model.search

import com.google.gson.annotations.SerializedName

/**
 * Created by gosullivan on 11/21/17.
 */
data class User(

        @SerializedName("friends_count")
        val friendsCount: Int,


        @SerializedName("listed_count")
        val listedCount: Int,

        @SerializedName("default_profile_image")
        val defaultProfileImage: Boolean,

        @SerializedName("favourites_count")
        val favouritesCount: Int,

        @SerializedName("description")
        val description: String,

        @SerializedName("created_at")
        val createdAt: String,

        @SerializedName("is_translator")
        val isTranslator: Boolean,

        @SerializedName("profile_background_image_url_https")
        val profileBackgroundImageUrlHttps:String,

        @SerializedName("protected")
        val jsonMemberProtected: Boolean,

        @SerializedName("screen_name")
        val screenName: String,

        @SerializedName("id_str")
        val idStr: String,

        @SerializedName("profile_link_color")
        val profileLinkColor: String,

        @SerializedName("is_translation_enabled")
        val isTranslationEnabled: Boolean,

        @SerializedName("id")
        val id: Long,

        @SerializedName("geo_enabled")
        val geoEnabled: Boolean,

        @SerializedName("profile_background_color")
        val profileBackgroundColor: String,

        @SerializedName("lang")
        val lang: String,

        @SerializedName("has_extended_profile")
        val hasExtendedProfile: Boolean,

        @SerializedName("profile_sidebar_border_color")
        val profileSidebarBorderColor: String,

        @SerializedName("profile_text_color")
        val profileTextColor: String,

        @SerializedName("verified")
        val verified: Boolean,

        @SerializedName("profile_image_url")
        val profileImageUrl: String,

        @SerializedName("contributors_enabled")
        val contributorsEnabled: Boolean,

        @SerializedName("profile_background_tile")
        val profileBackgroundTile: Boolean,

        @SerializedName("entities")
        val entities: Entities,

        @SerializedName("statuses_count")
        val statusesCount: Int,

        @SerializedName("followers_count")
        val followersCount: Int,

        @SerializedName("profile_use_background_image")
        val profileUseBackgroundImage: Boolean,

        @SerializedName("default_profile")
        val defaultProfile: Boolean,

        @SerializedName("name")
        val name: String,

        @SerializedName("location")
        val location: String,

        @SerializedName("profile_sidebar_fill_color")
        val profileSidebarFillColor: String

        )