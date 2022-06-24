package com.example.movieratings.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Ankita
 */
@Entity(tableName = "movie")
data class MovieItem(
    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("poster_path")
    var posterPath: String = "",
    @SerializedName("overview")
    var overview: String = "",
    @SerializedName("vote_average")
    var vote: Float = 0f,
    @SerializedName("runtime")
    var runtime: Int = 0,
    @SerializedName("original_language")
    var lang: String = "",
    var categoryId: Int = 0,
)

data class MovieItemList(
    @SerializedName("items")
    var movieItems: MutableList<MovieItem> = arrayListOf(),
)