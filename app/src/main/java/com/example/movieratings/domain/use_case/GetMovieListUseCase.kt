package com.example.movieratings.domain.use_case

import com.example.movieratings.common.Resource
import com.example.movieratings.domain.repository.MovieListRepository
import com.example.movieratings.models.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Ankita
 */
class GetMovieListUseCase @Inject constructor(private val repository: MovieListRepository) {

    operator fun invoke(id: Int): Flow<Resource<List<MovieItem>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.movieList(id)
            if (result != null)
                emit(Resource.Success(result.movieItems))
            else
                emit(Resource.Error("Something went wrong"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}