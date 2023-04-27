package com.example.tvmazecompose.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tvmazecompose.domain.model.MovieDetailModel
import com.example.tvmazecompose.domain.model.MovieModel
import com.example.tvmazecompose.domain.model.TalentModel

/**
 * Created by Erik Hernandez on 24/04/2023.
 */
@Database(entities = [MovieModel::class, MovieDetailModel::class, TalentModel::class], version = 1)
abstract class MovieDataSource : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
