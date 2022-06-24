package com.example.movieratings.models

/**
 * Created by Ankita
 */
data class MovieDataState(
    var isLoaded: Boolean = false,
    var mList: MutableList<MutableList<MovieItem>> = arrayListOf()
)
