package com.example.movieratings.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieratings.common.DataState
import com.example.movieratings.common.Resource
import com.example.movieratings.domain.use_case.GetMovieListUseCase
import com.example.movieratings.models.MovieItem
import com.example.movieratings.util.MOVIE_LIST_ID
import com.example.movieratings.util.TV_LIST_ID
import com.example.movieratings.util.WEB_SERIES_LIST_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Ankita
 */
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieList: GetMovieListUseCase,
) : ViewModel() {

    private var _listState = MutableLiveData<DataState<Boolean>>()
    val listState: LiveData<DataState<Boolean>> = _listState
    var movieItemList: MutableList<MovieItem> = arrayListOf()
    var mList: MutableList<MutableList<MovieItem>> = arrayListOf()

    fun getList(id: Int) {
        movieList(id).onEach { state ->
            when (state) {
                is Resource.Loading -> {
                    _listState.value = DataState(isLoading = true)
                }
                is Resource.Error -> {
                    _listState.value = DataState(error = state.message.toString())
                }
                is Resource.Success -> {
                    movieItemList = (state.data as MutableList<MovieItem>)
                    when (id) {
                        MOVIE_LIST_ID -> {
                            mList.add(0, movieItemList)
                            getList(TV_LIST_ID)
                        }
                        TV_LIST_ID -> {
                            mList.add(1, movieItemList)
                            getList(WEB_SERIES_LIST_ID)
                        }
                        WEB_SERIES_LIST_ID -> {
                            mList.add(2, movieItemList)
                            _listState.value = DataState(data = true)
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}