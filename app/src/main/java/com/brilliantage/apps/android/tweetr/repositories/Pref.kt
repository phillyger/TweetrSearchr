package com.brilliantage.apps.android.tweetr.repositories.providers

import com.marcinmoskala.kotlinpreferences.PreferenceHolder

/**
 * Created by gosullivan on 11/21/17.
 */
object Pref: PreferenceHolder() {
    var token: String? by bindToPreferenceFieldNullable("TokenKey")
}