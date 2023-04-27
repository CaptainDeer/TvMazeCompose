package com.example.tvmazecompose.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.tvmazecompose.domain.model.MovieModel
import com.example.tvmazecompose.ui.components.SearchComponent
import com.example.tvmazecompose.ui.components.TopAppBarComponent
import com.example.tvmazecompose.ui.theme.Onyx
import com.example.tvmazecompose.ui.theme.Orange
import com.example.tvmazecompose.ui.theme.White
import com.example.tvmazecompose.ui.viewmodels.MoviesViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Created by Erik Hernandez on 25/04/2023.
 */

/**
 * Funcion composable que contiene una instancia de la barra de busqueda y la lista de peliculas
 * @param viewModel MoviesViewModel para poder mostrar los datos
 * @param navHostController necesario para poder redireccionar a otras rutas
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.now().format(dateFormat)
    HomeComponents(viewModel, date) {
        navHostController.navigate(it)
    }
}

/**
 * Funcion composable que inicializa y organiza los componentes a mostrar
 * @param viewModel Necesario para usar el TopAppBarr y SearchComponent
 * @param date Fecha de hoy en tipo String formato ISO 8601
 * @param navigation String que nos dice cual es la proxima ruta en la navegacion
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun HomeComponents(
    viewModel: MoviesViewModel,
    date: String,
    navigation: (String) -> Unit
) {
    val list by viewModel.movies.observeAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBarComponent(viewModel)
        SearchComponent(viewModel = viewModel)
        LazyColumn {
            list?.let {
                items(it) {
                    Column {
                        MovieListItem(movieModel = it, viewModel, date, navigation)
                    }
                }
            }
        }
    }
}

/**
 * Funcion composable que muestra el item que aparece dentro de la lista
 * @param movieModel Modelo necesario para que la vista funcione
 * @param viewModel Funcionalidad al click
 * @param date Fecha de hoy en tipo String formato ISO 8601
 * @param navigation String que nos dice cual es la proxima ruta en la navegacion
 */
@Composable
private fun MovieListItem(
    movieModel: MovieModel,
    viewModel: MoviesViewModel,
    date: String,
    navigation: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.getDetails(movieModel.id.toString(), navigation) }
            .padding(horizontal = 12.dp, vertical = 6.dp),
        backgroundColor = Onyx,
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(movieModel.image),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp, 126.dp)
                    .padding(end = 12.dp)
                    .clip(RoundedCornerShape(6))
                    .border(2.dp, Orange, RoundedCornerShape(6))
            )
            Column(
                modifier = Modifier
                    .padding(start = 6.dp, end = 12.dp, top = 3.dp, bottom = 6.dp),
            ) {
                Text(
                    text = movieModel.movieName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = White
                )
                Text(
                    modifier = Modifier.padding(vertical = 6.dp),
                    text = movieModel.networkName,
                    color = White,
                    fontSize = 16.sp,
                )
                Row {
                    Text(
                        text = "$date | ",
                        color = White
                    )
                    Text(
                        text = movieModel.airtime,
                        color = White
                    )
                }
            }
        }
    }
}
