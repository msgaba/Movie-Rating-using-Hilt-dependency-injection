package com.example.movieratings.common

/**
 * Created by Ankita
 */
data class DataState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String = ""
)
