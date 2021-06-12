package com.kadirkuruca.offlinecaching.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _movieList : MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val movieList : LiveData<Resource<MovieResponse>> = _movieList

    init {
        viewModelScope.launch {
            _movieList.postValue(Resource.Loading())
            val movieResponse = movieRepository.getPopularMovies()
            _movieList.value = handleMovieResponse(movieResponse)
        }
    }

    private fun handleMovieResponse(response: Response<MovieResponse>): Resource<MovieResponse>{
        if(response.isSuccessful) {
            response.body()?.let{ movieResult ->
                return Resource.Success(movieResult)
            }
            return Resource.Error(response.message())
        }
        else
            return Resource.Error("An error occured. Try Again.")
    }

}