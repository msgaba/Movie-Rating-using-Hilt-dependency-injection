package com.example.movieratings.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieratings.common.DataState
import com.example.movieratings.common.MovieUIEvent
import com.example.movieratings.common.Resource
import com.example.movieratings.data.repository.DataRepository
import com.example.movieratings.domain.use_case.GetMovieListUseCase
import com.example.movieratings.models.MovieDataState
import com.example.movieratings.models.MovieItem
import com.example.movieratings.util.MOVIE_LIST_ID
import com.example.movieratings.util.TV_LIST_ID
import com.example.movieratings.util.WEB_SERIES_LIST_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ankita
 */
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMovieList: GetMovieListUseCase,
    private val repository: DataRepository,
) : ViewModel() {

    private var _listState = MutableLiveData<DataState<Boolean>>()
    val listState: LiveData<DataState<Boolean>> = _listState
    var dataState = MovieDataState()

    fun onEvent(event: MovieUIEvent) {
        when (event) {
            is MovieUIEvent.PopulateData -> {
                state()
            }
        }
    }

    private fun getList(id: Int) {
        getMovieList(id).onEach { state ->
            when (state) {
                is Resource.Loading -> {
                    _listState.value = DataState(isLoading = true)
                }
                is Resource.Error -> {
                    _listState.value = DataState(error = state.message.toString())
                }
                is Resource.Success -> {
                    val movieItemList = (state.data as MutableList<MovieItem>)
                    movieItemList.forEach { it.categoryId = id }
                    repository.addList(movieItemList)
                    when (id) {
                        MOVIE_LIST_ID -> {
                            // second list api call
                            getList(TV_LIST_ID)
                        }
                        TV_LIST_ID -> {
                            // third list api call
                            getList(WEB_SERIES_LIST_ID)
                        }
                        WEB_SERIES_LIST_ID -> {
                            state()
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun state() {
        GlobalScope.launch(Dispatchers.IO) {
            val list = repository.getMovieList(MOVIE_LIST_ID)
            if (list.isNotEmpty()) {
                dataState.isLoaded = true
                dataState.mList.add(list as MutableList<MovieItem>)
                dataState.mList.add(repository.getMovieList(TV_LIST_ID) as MutableList<MovieItem>)
                dataState.mList.add(repository.getMovieList(WEB_SERIES_LIST_ID) as MutableList<MovieItem>)
                _listState.postValue(DataState(data = true))
            } else getList(MOVIE_LIST_ID)
        }
    }
}