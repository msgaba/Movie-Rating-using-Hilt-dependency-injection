package com.example.movieratings.domain.repository

import com.example.movieratings.models.MovieItem

/**
 * Created by Ankita
 */
interface DetailsRepository {
    suspend fun details(id: Int): MovieItem
}