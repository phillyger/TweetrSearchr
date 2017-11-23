package com.brilliantage.apps.android.tweetr.model.search.token

import com.google.gson.annotations.SerializedName

/**
 * Created by gosullivan on 11/22/17.
 */
data class AccessToken(
        @SerializedName("token_type")
        val tokenType:String,

        @SerializedName("access_token")
        val accessToken:String

)