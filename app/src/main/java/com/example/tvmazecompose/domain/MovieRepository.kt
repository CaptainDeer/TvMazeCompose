package com.example.tvmazecompose.domain

import com.example.tvmazecompose.data.model.Movie
import com.example.tvmazecompose.data.model.Search
import com.example.tvmazecompose.data.model.Show
import com.example.tvmazecompose.data.model.Talent
import com.example.tvmazecompose.domain.model.MovieDetailModel
import com.example.tvmazecompose.domain.model.MovieModel
import com.example.tvmazecompose.domain.model.TalentModel

/**
 * Created by Erik Hernandez on 24/04/2023.
 */
interface MovieRepository {
    suspend fun getMovies(date: String): MutableList<Movie>
    suspend fun getDetails(id: String): Show
    suspend fun getTalents(id: String): MutableList<Talent>
    suspend fun getSearchMovie(search: String): MutableList<Search>
    suspend fun getLocalMovies(): MutableList<MovieModel>
    suspend fun getLocalDetails(): MovieDetailModel
    suspend fun getLocalTalents(): MutableList<TalentModel>
    suspend fun delete()
    suspend fun deleteDetails()
    suspend fun deleteTalents()
}
