package com.example.movieratings.data.repository

import com.example.movieratings.data.api.ServiceApi
import com.example.movieratings.domain.repository.MovieListRepository
import com.example.movieratings.models.MovieItemList
import javax.inject.Inject

/**
 * Created by Ankita
 */
class MovieListRepositoryImpl @Inject constructor(private val api: ServiceApi) :
    MovieListRepository {
    override suspend fun movieList(id: Int): MovieItemList {
        return api.getMovieList(id, "2d66e20fd04b7f1b48b6a9bf65b3fedc", "en-US")
    }
}