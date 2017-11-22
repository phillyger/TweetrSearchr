package com.brilliantage.apps.android.tweetr.api

import android.util.Base64
import android.util.Log
import com.brilliantage.apps.android.tweetr.BuildConfig


import org.json.JSONException
import org.json.JSONObject

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * Created by gosullivan on 11/21/17.
 */
class AccessTokenProvidingInterceptor :Interceptor {

    private var token:String? = null

    override fun intercept(chain: Interceptor.Chain?): Response {

        var request = chain?.request()

        if (token == null) {
            val body = chain?.proceed(getToken())?.body() as? ResponseBody

            body?.let {
                try {
                    val jsonObject = JSONObject(it.string())
                    token = "Bearer " + jsonObject.optString("access_token")
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Log.d(AccessTokenProvidingInterceptor::class.java.name, "Error fetching token")
                }
            }

        }

        request = request?.newBuilder()
                ?.addHeader("Authorization", token)
                ?.build() as? Request

        return chain!!.proceed(request)
    }

    private fun getToken(): Request {
        val bearerToken = BuildConfig.CONSUMER_KEY +
                ":" + BuildConfig.CONSUMER_SECRET

        val base64BearerToken = "Basic " + Base64.encodeToString(bearerToken.toByteArray(), Base64.NO_WRAP)
        val requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8"), "grant_type=client_credentials")

        return Request.Builder()
                .url(BuildConfig.AUTH_END_POINT)
                .post(requestBody)
                .header("Authorization", base64BearerToken)
                .header("Content-Encoding", "gzip")
                .header("User-Agent", "My Twitter App v1.0.23")
                .header("Content-type", "application/x-www-form-urlencoded;charset=UTF-8")
                .build()
    }

}