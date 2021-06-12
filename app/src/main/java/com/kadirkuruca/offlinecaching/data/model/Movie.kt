package com.kadirkuruca.offlinecaching.data.model

import com.kadirkuruca.offlinecaching.util.API_URL

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String?,
    val overview: String?,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
){
    val poster_url: String? get(){
        poster_path?.let {
            return "https://image.tmdb.org/t/p/w500/"+poster_path
        }
        return null
    }
}