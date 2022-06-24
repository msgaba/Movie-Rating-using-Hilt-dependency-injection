package com.example.movieratings.data.repository

import androidx.annotation.WorkerThread
import com.example.movieratings.database.MovieDao
import com.example.movieratings.models.MovieItem
import javax.inject.Inject

/**
 * Created by Ankita
 */
class DataRepository @Inject constructor(private val movieDao: MovieDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addList(movieList: List<MovieItem>) {
        return movieDao.addList(movieList)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getMovieList(categoryId: Int): List<MovieItem> {
        return movieDao.getMovieList(categoryId)
    }
}