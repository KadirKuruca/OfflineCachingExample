package com.kadirkuruca.offlinecaching.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kadirkuruca.offlinecaching.data.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
}