package com.example.movieratings.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieratings.models.MovieItem

/**
 * Created by Ankita
 */
@Dao
interface MovieDao {

    /** select query to read all the entries in database **/
    /** Flow gives instant updates to the classes and reflects changes as soon as they occur **/
    @Query("SELECT * FROM movie WHERE categoryId = :categoryId")
    suspend fun getMovieList(categoryId: Int): List<MovieItem>

    /** insert query to add entry at the end in movie database **/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addList(movieList: List<MovieItem>)

}