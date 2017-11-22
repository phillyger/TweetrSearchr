package com.brilliantage.apps.android.tweetr.repositories.providers

/**
 * Created by gosullivan on 11/21/17.
 */
abstract class Provider<T>(val initializer: ()->T) {

    var override: T? = null
    var original: T? = null

    fun get(): T = override ?: original ?: initializer().apply { original = this }
    fun lazyGet(): Lazy<T> = lazy { get() }

}