package com.example.tvmazecompose.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvmazecompose.core.navigation.Routes
import com.example.tvmazecompose.domain.MovieRepository
import com.example.tvmazecompose.domain.model.MovieDetailModel
import com.example.tvmazecompose.domain.model.MovieModel
import com.example.tvmazecompose.domain.model.TalentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Erik Hernandez on 25/04/2023.
 */
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepositoryImpl: MovieRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData(false)

    val isLoading: MutableLiveData<Boolean> get() = _isLoading

    private val _movies = MutableLiveData<MutableList<MovieModel>>()

    val movies: MutableLiveData<MutableList<MovieModel>> get() = _movies

    private val _movieDetail = MutableLiveData<MovieDetailModel>()

    val movieDetail: MutableLiveData<MovieDetailModel> get() = _movieDetail

    private val _movieTalents = MutableLiveData<MutableList<TalentModel>>()

    val movieTalents: MutableLiveData<MutableList<TalentModel>> get() = _movieTalents

    private val _topAppBarTitle = MutableLiveData<String>()

    val topAppBarTitle: MutableLiveData<String> get() = _topAppBarTitle

    private val _search = MutableLiveData("")

    val search: MutableLiveData<String> get() = _search

    /**
     * Realiza la llamada al servidor
     * Consigue los datos y los plazma en la base de datos local
     * Tambien se agrega informacion para top bar y loading
     * @param date fecha del dia de hoy
     * @param navigation Se va a homescreen en esta funcion
     */
    fun getMovies(date: String, navigation: (String) -> Unit) {
        if (_isLoading.value == false) {
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                _topAppBarTitle.postValue(date)
                movieRepositoryImpl.getMovies(date)
                _movies.postValue(movieRepositoryImpl.getLocalMovies())
                _isLoading.postValue(false)
            }
            navigation(Routes.HomeScreen.routes)
        }
    }

    /**
     * Realiza la llamada al servidor
     * Consigue los datos y los plazma en la base de datos local
     * Tambien se agrega informacion para top bar y loading
     * @param id del show
     * @param navigation proximo destino seria moviedetailscreen
     */
    fun getDetails(id: String, navigation: (String) -> Unit) {
        if (_isLoading.value == false) {
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                movieRepositoryImpl.getDetails(id)
                movieRepositoryImpl.getTalents(id)
                _movieTalents.postValue(movieRepositoryImpl.getLocalTalents())
                _movieDetail.postValue(movieRepositoryImpl.getLocalDetails())
                _topAppBarTitle.postValue(movieRepositoryImpl.getDetails(id).name)
                _isLoading.postValue(false)
            }
            navigation(Routes.MovieDetailScreen.routes)
        }
    }

    /**
     * Realiza la llamada al servidor
     * Consigue los datos y los plazma en la base de datos local
     * Tambien se agrega informacion para top bar y loading
     * @param search texto que se encuentra en el textfield
     */
    fun searchMovie(
        search: String
    ) {
        if (_isLoading.value == false) {
            viewModelScope.launch(Dispatchers.IO) {
                _isLoading.postValue(true)
                _topAppBarTitle.postValue(search)
                delete()
                _movies.postValue(movieRepositoryImpl.getLocalMovies())
                movieRepositoryImpl.getSearchMovie(search)
                _movies.postValue(movieRepositoryImpl.getLocalMovies())
                _isLoading.postValue(false)
            }
        }
    }

    fun delete() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepositoryImpl.delete()
        }
    }

    fun deleteDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepositoryImpl.deleteDetails()
        }
    }

    fun deleteTalents() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepositoryImpl.deleteTalents()
        }
    }
}
