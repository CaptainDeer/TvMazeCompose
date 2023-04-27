package com.example.tvmazecompose.data

import com.example.tvmazecompose.data.api.MovieAPI
import com.example.tvmazecompose.data.model.*
import com.example.tvmazecompose.domain.MovieDao
import com.example.tvmazecompose.domain.MovieRepository
import com.example.tvmazecompose.domain.model.MovieDetailModel
import com.example.tvmazecompose.domain.model.MovieModel
import com.example.tvmazecompose.domain.model.TalentModel
import javax.inject.Inject

/**
 * Created by Erik Hernandez on 25/04/2023.
 */
class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieAPI,
    private val movieDao: MovieDao
) : MovieRepository {

    /**
     * @param date dia actual para poder hacer la consulta
     * @return Regresa una lista mutable de objeto Movie
     */
    override suspend fun getMovies(date: String): MutableList<Movie> {
        val movies = movieService.getMovies(date)
        movies.map { it.toDomain() }.forEach {
            movieDao.insert(it)
        }
        return movies
    }

    /**
     * @param id id para conseguir los detalles de la pelicula
     * @return Regresa un objeto Show
     */
    override suspend fun getDetails(id: String): Show {
        val movieDetail = movieService.getMovieDetails(id)
        movieDao.insertDetails(movieDetail.toDomain())
        return movieDetail
    }

    /**
     * @param id id para conseguir los talentos de la pelicula
     * @return Regresa una lista mutable de Talent
     */
    override suspend fun getTalents(id: String): MutableList<Talent> {
        val talents = movieService.getMovieCast(id)
        talents.map { it.toDomain() }.forEach {
            movieDao.insertCast(it)
        }
        return talents
    }

    /**
     * @param search Texto que esta en el buscador
     * @return Regresa una lista mutable de objeto Search
     */
    override suspend fun getSearchMovie(search: String): MutableList<Search> {
        val listSearchMovies = movieService.getSearchMovie(search)
        listSearchMovies.map { it.toDomain() }.forEach {
            movieDao.insert(it)
        }
        return listSearchMovies
    }

    /**
     * Consigue de manera local las peliculas para la pantalla principal
     */
    override suspend fun getLocalMovies(): MutableList<MovieModel> = movieDao.getAll()

    /**
     * Consigue de manera local los detalles de la pelicula
     */
    override suspend fun getLocalDetails(): MovieDetailModel = movieDao.getDetails()

    /**
     * Consigue de manera local los talentos de la pelicula
     */
    override suspend fun getLocalTalents(): MutableList<TalentModel> = movieDao.getTalents()


    override suspend fun delete() {
        movieDao.delete()
    }

    override suspend fun deleteDetails() {
        movieDao.deleteDetails()
    }

    override suspend fun deleteTalents() {
        movieDao.deleteTalents()
    }
}
