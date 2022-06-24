package com.example.movieratings.hilt

import com.example.movieratings.data.api.ServiceApi
import com.example.movieratings.data.repository.DetailsRepositoryImpl
import com.example.movieratings.data.repository.MovieListRepositoryImpl
import com.example.movieratings.domain.repository.DetailsRepository
import com.example.movieratings.domain.repository.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Ankita
 */
@Module
@InstallIn(SingletonComponent::class)

/** defining dependency for [ServiceApi] class **/
object ApiModule {
    @Provides
    @Singleton
    fun serviceApi(): ServiceApi {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServiceApi::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    /** defining dependency for [DetailsRepository] class **/
    @Binds
    abstract fun detailsRepository(detailsRepositoryImpl: DetailsRepositoryImpl): DetailsRepository

    /** defining dependency for [MovieListRepository] class **/
    @Binds
    abstract fun movieListRepository(movieListRepositoryImpl: MovieListRepositoryImpl): MovieListRepository
}