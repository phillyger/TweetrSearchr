package com.brilliantage.apps.android.tweetr.utils

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder

/**
 * Created by gosullivan on 11/22/17.
 */
fun Context.toast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

val TextView.trimmedText: String
    get() = text.toString().trim()

var TextView.textOrHide: String?
    get() = if (visibility == View.GONE) "" else text.toString()
    set(value) {
        isVisible = !value.isNullOrBlank()
        text = value
    }

fun TextView.setTextOrHide(id: Int?) {
    if(id == null) isVisible = false else setText(id)
}

fun ImageView.playAnimation() {
    val animationDrawable = background as AnimationDrawable
    animationDrawable.isOneShot = false
    animationDrawable.start()
}

fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.loadUrl(url:String){
    Glide.with(context)
            .load(url)
            .into(this)
}