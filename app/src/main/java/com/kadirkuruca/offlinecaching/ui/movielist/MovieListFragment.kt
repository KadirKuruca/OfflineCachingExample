package com.kadirkuruca.offlinecaching.ui.movielist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.kadirkuruca.offlinecaching.R
import com.kadirkuruca.offlinecaching.databinding.FragmentMovieListBinding

class MovieListFragment: Fragment(R.layout.fragment_movie_list) {

    private lateinit var binding: FragmentMovieListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieListBinding.bind(view)
    }
}