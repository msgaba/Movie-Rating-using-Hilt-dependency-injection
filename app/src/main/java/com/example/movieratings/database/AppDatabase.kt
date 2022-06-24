package com.example.movieratings.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieratings.models.MovieItem

/**
 * Created by Ankita
 */
@Database(entities = [MovieItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}