package com.example.movieratings.util

import android.content.Context
import android.view.View
import android.view.View.*
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Ankita
 */
const val MOVIE_LIST_ID = 1
const val TV_LIST_ID = 2
const val WEB_SERIES_LIST_ID = 3
const val KEY_MOVIE_ID = "key_movie_id"
fun View.visibleOnCondition(isVisible: Boolean) {
    visibility = if (isVisible) VISIBLE
    else GONE
}

fun View.invisibleOnCondition(isInvisible: Boolean) {
    visibility = if (isInvisible) INVISIBLE
    else VISIBLE
}

fun View.hide(invisible: Boolean = false) {
    this.visibility = if (invisible) INVISIBLE else GONE
}

fun View.show() {
    this.visibility = VISIBLE
}

fun ImageView.loadImage(context: Context, url: String) {
    Glide.with(context)
        .asBitmap()
        .apply(RequestOptions().fitCenter())
        .load(url)
        .into(this)
}