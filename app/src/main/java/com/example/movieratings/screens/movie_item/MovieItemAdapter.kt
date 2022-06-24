package com.example.movieratings.screens.movie_item

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieratings.databinding.ItemMovieBinding
import com.example.movieratings.models.MovieItem

/**
 * Created by Ankita
 */
class MovieItemAdapter(private val mContext: Activity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mMovieMovieItemList: MutableList<MovieItem> = arrayListOf()
    private lateinit var mListener: MovieItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieItemViewHolder(mContext, binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder: MovieItemViewHolder = holder as MovieItemViewHolder
        viewHolder.listener = mListener
        viewHolder.bind(mMovieMovieItemList[position], position)
    }

    override fun getItemCount(): Int = mMovieMovieItemList.size

    fun list(list: MutableList<MovieItem>) {
        mMovieMovieItemList = list
        notifyDataSetChanged()
    }

    fun listener(listener: MovieItemClickListener) {
        mListener = listener
    }
}