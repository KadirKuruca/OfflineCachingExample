package com.kadirkuruca.offlinecaching.data.remote

import com.kadirkuruca.offlinecaching.BuildConfig
import com.kadirkuruca.offlinecaching.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apikey: String = BuildConfig.TMDB_API_KEY
    ): Response<MovieResponse>
}