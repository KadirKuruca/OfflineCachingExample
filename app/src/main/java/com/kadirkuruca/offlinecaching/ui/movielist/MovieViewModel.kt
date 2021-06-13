package com.kadirkuruca.offlinecaching.ui.movielist

import androidx.lifecycle.*
import com.kadirkuruca.offlinecaching.data.model.MovieResponse
import com.kadirkuruca.offlinecaching.repository.MovieRepository
import com.kadirkuruca.offlinecaching.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor (
    private val movieRepository: MovieRepository
): ViewModel() {

    val movieList = movieRepository.getPopularMovies().asLiveData()
}