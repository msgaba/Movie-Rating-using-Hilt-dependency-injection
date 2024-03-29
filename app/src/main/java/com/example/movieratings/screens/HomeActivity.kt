package com.example.movieratings.screens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.movieratings.common.MovieUIEvent
import com.example.movieratings.databinding.ActivityHomeBinding
import com.example.movieratings.models.headerList
import com.example.movieratings.models.modifyHeader
import com.example.movieratings.screens.header.HeaderAdapter
import com.example.movieratings.screens.header.HeaderItemClickListener
import com.example.movieratings.screens.movie_detail.MovieDetailActivity.Companion.navigate
import com.example.movieratings.util.ViewPagerAdapter
import com.example.movieratings.util.hide
import com.example.movieratings.util.show
import com.example.movieratings.util.visibleOnCondition
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), HeaderItemClickListener,
    ViewPagerAdapter.ItemClickListener {

    private lateinit var binding: ActivityHomeBinding
    private val mViewModel: MovieListViewModel by viewModels()

    private lateinit var headerAdapter: HeaderAdapter
    private lateinit var headerLayoutManager: LinearLayoutManager
    private var adapter: ViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.viewSplash.splashContainer.hide()
        }, 3000)
        viewClicks()
        observer()
        headerSetup()
        mViewModel.onEvent(MovieUIEvent.PopulateData)
    }

    /** view click actions **/
    private fun viewClicks() {
        binding.error.errorContainer.setOnClickListener {
            binding.error.errorContainer.hide()
            loading(true)
            mViewModel.onEvent(MovieUIEvent.PopulateData)
        }
    }

    /** header setup **/
    private fun headerSetup() {
        headerAdapter = HeaderAdapter(this)
        headerLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        headerAdapter.listener(this)
        binding.header.apply {
            adapter = headerAdapter
            layoutManager = headerLayoutManager
        }
        headerAdapter.list(headerList())
    }

    /** callback for header clicks **/
    override fun onHeaderClicked(pos: Int) {
        headerAdapter.list(modifyHeader(headerAdapter.list(), pos))
        binding.viewPager.currentItem = pos
    }

    /** list view pager setup **/
    private fun viewPagerSetup(viewPager: ViewPager?) {
        adapter = ViewPagerAdapter(this, mViewModel.dataState.mList, this)
        viewPager!!.adapter = adapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (!headerAdapter.list()[position].isSelected) headerAdapter.list(
                    modifyHeader(
                        headerAdapter.list(),
                        position
                    )
                )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    /** observer for events in viewmodel **/
    private fun observer() {
        mViewModel.listState.observe(this) { state ->
            when {
                state.isLoading -> {
                    loading(true)
                }
                state.error.isNotEmpty() -> {
                    binding.apply {
                        loader.hide()
                        viewPager.hide()
                        error.errorContainer.show()
                    }
                }
                state.data == true -> {
                    loading(false)
                    viewPagerSetup(binding.viewPager)
                }
            }
        }
    }

    /** method to maintain loading state of UI **/
    private fun loading(isLoading: Boolean) {
        binding.apply {
            loader.visibleOnCondition(isLoading)
            viewPager.visibleOnCondition(!isLoading)
        }
    }

    /** callback for list item click **/
    override fun onItemClicked(id: Int) {
        navigate(this, id)
    }

}