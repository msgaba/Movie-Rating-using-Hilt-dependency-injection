package com.example.movieratings.domain.use_case

import com.example.movieratings.common.Resource
import com.example.movieratings.domain.repository.DetailsRepository
import com.example.movieratings.models.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Ankita
 */
class GetDetailsUseCase @Inject constructor(private val repository: DetailsRepository) {

    operator fun invoke(id: Int): Flow<Resource<MovieItem>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.details(id)
            if (result != null)
                emit(Resource.Success(result))
            else
                emit(Resource.Error("Something went wrong"))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}