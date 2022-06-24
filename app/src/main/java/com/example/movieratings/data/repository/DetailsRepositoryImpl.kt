package com.example.movieratings.data.repository

import com.example.movieratings.BuildConfig
import com.example.movieratings.data.api.ServiceApi
import com.example.movieratings.domain.repository.DetailsRepository
import com.example.movieratings.models.MovieItem
import javax.inject.Inject

/**
 * Created by Ankita
 */
class DetailsRepositoryImpl @Inject constructor(private val api: ServiceApi) : DetailsRepository {
    override suspend fun details(id: Int): MovieItem {
        return api.getDetails(id, BuildConfig.API_KEY, "en-US")
    }
}