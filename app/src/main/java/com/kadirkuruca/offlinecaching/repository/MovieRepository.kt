package com.kadirkuruca.offlinecaching.repository

import androidx.room.withTransaction
import com.kadirkuruca.offlinecaching.data.local.MovieDatabase
import com.kadirkuruca.offlinecaching.data.model.MovieResponse
import com.kadirkuruca.offlinecaching.data.remote.MovieApi
import com.kadirkuruca.offlinecaching.util.networkBoundResource
import kotlinx.coroutines.delay
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class MovieRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
) {
    private val movieDao = movieDatabase.movieDao()

    fun getPopularMovies() = networkBoundResource(
        query = {
            movieDao.getAllMovies()
        },
        fetch = {
            delay(2000)
            movieApi.getPopularMovies()
        },
        saveFetchResult = { movieResponse ->
            if(movieResponse.isSuccessful){
                movieResponse.body()?.let {
                    movieDatabase.withTransaction {
                        movieDao.deleteAllMovies()
                        movieDao.insertMovies(it.results)
                    }
                }
            }
        }
    )
}