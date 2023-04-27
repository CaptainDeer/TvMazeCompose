package com.example.tvmazecompose.data.api

import com.example.tvmazecompose.data.model.Movie
import com.example.tvmazecompose.data.model.Search
import com.example.tvmazecompose.data.model.Show
import com.example.tvmazecompose.data.model.Talent
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Erik Hernandez on 25/04/2023.
 */
interface MovieAPI {
    @GET("schedule?country=US")
    suspend fun getMovies(
        @Query("date") date: String
    ): MutableList<Movie>

    @GET("shows/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: String
    ): Show

    @GET("shows/{id}/cast")
    suspend fun getMovieCast(
        @Path("id") id: String
    ): MutableList<Talent>

    @GET("search/shows?")
    suspend fun getSearchMovie(
        @Query("q") search: String
    ): MutableList<Search>
}