package com.kadirkuruca.offlinecaching.ui.movielist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kadirkuruca.offlinecaching.R
import com.kadirkuruca.offlinecaching.adapter.MovieAdapter
import com.kadirkuruca.offlinecaching.databinding.FragmentMovieListBinding
import com.kadirkuruca.offlinecaching.util.Resource
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MovieListFragment"

@AndroidEntryPoint
class MovieListFragment: Fragment(R.layout.fragment_movie_list) {

    private val viewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieListBinding.bind(view)

        val movieAdapter = MovieAdapter()
        binding.apply {
            rvMovies.apply {
                adapter = movieAdapter
                setHasFixedSize(true)
//                addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            }

            viewModel.movieList.observe(viewLifecycleOwner){ result ->
                movieAdapter.submitList(result.data)
                progressMovies.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                tvErrorMessage.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                tvErrorMessage.text = result.error?.localizedMessage
                Log.e(TAG, "Error: ${result.error.toString()}")

            //                when(it){
//                    is Resource.Success -> {
//                        progressMovies.visibility = View.INVISIBLE
//                        it.data?.let { movieList ->
//                            movieAdapter.submitList(movieList)
//                        }
//                    }
//                    is Resource.Error -> {
//                        progressMovies.visibility = View.INVISIBLE
//                        Log.e(TAG, "Error: ${it.error.toString()}")
//                        tvErrorMessage.text = it.error?.localizedMessage
//                    }
//                    is Resource.Loading -> {
//                        progressMovies.visibility = View.VISIBLE
//                    }
//                }
            }
        }
    }
}