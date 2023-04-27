package com.example.tvmazecompose.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tvmazecompose.domain.model.MovieDetailModel
import com.example.tvmazecompose.domain.model.MovieModel
import com.example.tvmazecompose.domain.model.TalentModel

/**
 * Created by Erik Hernandez on 24/04/2023.
 */
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieModel: MovieModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetails(movieDetailModel: MovieDetailModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCast(talentModel: TalentModel)

    @Query("SELECT * FROM movies")
    fun getAll(): MutableList<MovieModel>

    @Query("SELECT * FROM movie_detail")
    fun getDetails(): MovieDetailModel

    @Query("SELECT * FROM talents")
    fun getTalents(): MutableList<TalentModel>

    @Query("DELETE FROM movies")
    fun delete()

    @Query("DELETE FROM movie_detail")
    fun deleteDetails()

    @Query("DELETE FROM talents")
    fun deleteTalents()
}
