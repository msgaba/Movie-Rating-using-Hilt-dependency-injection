package com.example.movieratings.domain.repository

import com.example.movieratings.models.MovieItemList

/**
 * Created by Ankita
 */
interface MovieListRepository {
    suspend fun movieList(id: Int): MovieItemList
}