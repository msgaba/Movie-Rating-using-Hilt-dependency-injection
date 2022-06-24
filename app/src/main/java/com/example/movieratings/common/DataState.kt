package com.example.movieratings.common

/**
 * Created by Ankita
 * class to maintain data state when response is received from api
 */
data class DataState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val error: String = ""
)
