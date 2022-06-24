package com.example.movieratings.util

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.movieratings.R
import com.example.movieratings.models.MovieItem
import com.example.movieratings.screens.movie_item.MovieItemAdapter
import com.example.movieratings.screens.movie_item.MovieItemClickListener
import java.util.*

class ViewPagerAdapter(
    var context: Context,
    private var movieItemList: List<MutableList<MovieItem>>,
    private val listener: ItemClickListener
) :
    PagerAdapter(), MovieItemClickListener {

    private var mLayoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var list: RecyclerView
    private lateinit var movieItemAdapter: MovieItemAdapter
    private lateinit var mLayoutManager: LinearLayoutManager

    override fun getCount(): Int {
        return movieItemList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false)
        list = itemView.findViewById(R.id.list)
        Objects.requireNonNull(container).addView(itemView)
        movieItemAdapter = MovieItemAdapter(context as Activity)
        mLayoutManager = LinearLayoutManager(context)
        list.apply {
            adapter = movieItemAdapter
            layoutManager = mLayoutManager
        }
        movieItemAdapter.listener(this)
        movieItemAdapter.list(movieItemList[position])
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    interface ItemClickListener {
        fun onItemClicked(id: Int)
    }

    override fun onMovieItemClicked(id: Int) {
        listener.onItemClicked(id)
    }
}

