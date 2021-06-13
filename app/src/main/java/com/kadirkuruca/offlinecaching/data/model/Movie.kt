package com.kadirkuruca.offlinecaching.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @ColumnInfo(name = "movieId")
    @PrimaryKey
    val id: Int = 0,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val vote_average: Double,
){
    val poster_url: String? get(){
        poster_path?.let {
            return "https://image.tmdb.org/t/p/w500/"+poster_path
        }
        return null
    }
}