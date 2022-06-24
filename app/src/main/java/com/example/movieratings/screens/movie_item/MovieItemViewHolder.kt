package com.example.movieratings.screens.movie_item

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.movieratings.databinding.ItemMovieBinding
import com.example.movieratings.models.MovieItem
import com.example.movieratings.util.loadImage

/**
 * Created by Ankita
 */
class MovieItemViewHolder(
    private val mContext: Context,
    private val viewBinding: ItemMovieBinding
) :
    RecyclerView.ViewHolder(viewBinding.root) {

    lateinit var listener: MovieItemClickListener

    fun bind(movieItem: MovieItem, pos: Int) {
        viewBinding.apply {
            title.text = movieItem.title
            serial.text = (pos + 1).toString()
            rating.text = movieItem.vote.toString()
            val url = "https://image.tmdb.org/t/p/original" + movieItem.posterPath
            image.loadImage(mContext, url)
            movieItemContainer.setOnClickListener {
                listener.onMovieItemClicked(movieItem.id)
            }
        }
    }
}

interface MovieItemClickListener {
    fun onMovieItemClicked(id: Int)
}