package com.example.movieratings.screens.movie_detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.movieratings.databinding.ActivityMovieDetailBinding
import com.example.movieratings.util.*
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Ankita
 */
@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private val mViewModel: MovieDetailViewModel by viewModels()

    private lateinit var binding: ActivityMovieDetailBinding
    private var movieId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        extras()
        observer()
        viewClicks()
        mViewModel.details(movieId)
    }

    /** extracting intent extras **/
    private fun extras() {
        intent?.extras?.let {
            if (it.containsKey(KEY_MOVIE_ID))
                movieId = it.getInt(KEY_MOVIE_ID)
        }
    }

    /** view click actions **/
    private fun viewClicks() {
        // click action for back button
        binding.back.setOnClickListener {
            onBackPressed()
        }
        // click action for error view
        binding.error.errorContainer.setOnClickListener {
            binding.error.errorContainer.hide()
            loading(true)
            mViewModel.details(movieId)
        }
    }

    /** state observer for events in viewmodel **/
    private fun observer() {
        mViewModel.detailState.observe(this) { state ->
            when {
                state.isLoading -> {
                    loading(true)
                }
                state.error.isNotEmpty() -> {
                    binding.apply {
                        loader.hide()
                        viewGroup.hide()
                        error.errorContainer.show()
                    }
                }
                state.data == true -> {
                    loading(false)
                    populateData()
                }
            }
        }
    }

    /** method to maintain loading state of UI **/
    private fun loading(isLoading: Boolean) {
        binding.apply {
            loader.visibleOnCondition(isLoading)
            viewGroup.visibleOnCondition(!isLoading)
        }
    }

    /** populating data on UI **/
    private fun populateData() {
        binding.apply {
            name.text = mViewModel.movieItem.title
            val url = "https://image.tmdb.org/t/p/original" + mViewModel.movieItem.posterPath
            image.loadImage(this@MovieDetailActivity, url)
            description.text = mViewModel.movieItem.overview
            stats.vote.text = mViewModel.movieItem.vote.toString()
            stats.runtime.text = mViewModel.movieItem.runtime.toString()
            stats.language.text = mViewModel.movieItem.lang
        }
    }

    companion object {
        fun navigate(context: Activity, movieId: Int) {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(KEY_MOVIE_ID, movieId)
            context.startActivity(intent)
        }
    }
}