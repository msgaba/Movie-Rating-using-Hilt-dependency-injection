package com.example.movieratings.data.api

import com.example.movieratings.models.MovieItem
import com.example.movieratings.models.MovieItemList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Ankita
 */
interface ServiceApi {

    @GET("list/{listId}")
    suspend fun getMovieList(
        @Path("listId") listId: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): MovieItemList

    @GET("movie/{movieId}")
    suspend fun getDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): MovieItem
}