package com.example.movieratings.screens.movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieratings.common.DataState
import com.example.movieratings.common.Resource
import com.example.movieratings.domain.use_case.GetDetailsUseCase
import com.example.movieratings.models.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Ankita
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetails: GetDetailsUseCase
) : ViewModel() {

    private var _detailState = MutableLiveData<DataState<Boolean>>()
    val detailState: LiveData<DataState<Boolean>> = _detailState

    lateinit var movieItem: MovieItem

    fun details(id: Int) {
        movieDetails(id).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _detailState.value = DataState(isLoading = true)
                }
                is Resource.Error -> {
                    _detailState.value = DataState(error = result.message.toString())
                }
                is Resource.Success -> {
                    movieItem = result.data!!
                    _detailState.value = DataState(data = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}