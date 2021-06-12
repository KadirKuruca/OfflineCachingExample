package com.kadirkuruca.offlinecaching.repository

import com.kadirkuruca.offlinecaching.data.model.MovieResponse
import com.kadirkuruca.offlinecaching.data.remote.MovieApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieApi: MovieApi
) {

    suspend fun getPopularMovies(): Response<MovieResponse> {
        return movieApi.getPopularMovies()
    }
}