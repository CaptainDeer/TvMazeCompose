package com.example.tvmazecompose.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.tvmazecompose.domain.model.TalentModel
import com.example.tvmazecompose.ui.components.TopAppBarComponent
import com.example.tvmazecompose.ui.theme.LapisLazuli
import com.example.tvmazecompose.ui.theme.Orange
import com.example.tvmazecompose.ui.theme.White
import com.example.tvmazecompose.ui.viewmodels.MoviesViewModel

/**
 * Created by Erik Hernandez on 26/04/2023.
 */


/**
 * Funcion composable que contiene los detalles de la pelicula seleccionada
 * @param viewModel MoviesViewModel para poder mostrar los datos
 */
@Composable
fun MovieDetailScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
) {
    viewModel.deleteDetails()
    viewModel.deleteTalents()
    MovieDetailComponents(viewModel)
}

/**
 * Funcion composable que organiza y distribuye los componentes a mostrar en pantalla
 * @param viewModel MoviesViewModel para poder mostrar los datos
 */
@Composable
private fun MovieDetailComponents(
    viewModel: MoviesViewModel,
) {
    val details by viewModel.movieDetail.observeAsState()
    val talents by viewModel.movieTalents.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBarComponent(viewModel)
        Row {
            MovieImage(url = details?.image ?: "")
            Column {
                MovieDetails(
                    movieName = details?.movieName ?: "",
                    networkName = details?.networkName ?: "",
                    rating = details?.rating ?: ""
                )
                GoTo(officialSite = details?.officialSite ?: "")
            }
        }
        MovieDescription(
            summary = details?.summary ?: "",
            genres = details?.genres ?: "",
            time = details?.time ?: "",
            days = details?.days ?: ""
        )
        TalentsComponent(talents)
    }
}

/**
 * Funcion composable que contiene caracteristicas para mostrar el componente de imagen
 * @param url String que nos dice cual es el Link de la imagen
 */
@Composable
private fun MovieImage(url: String) {
    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(160.dp, 210.dp)
            .padding(12.dp)
            .clip(RoundedCornerShape(6))
            .border(2.dp, Orange, RoundedCornerShape(6))
    )
}

/**
 * Funcion composable que trabaja con la parte de la descripcion de la pantalla de detalles
 * @param movieName Objeto con la info necesaria para mostrar en pantalla
 * @param networkName Dato complementario para mostrar en UI
 * @param rating Dato complementario para mostrar en UI
 */
@Composable
private fun MovieDetails(
    movieName: String,
    networkName: String,
    rating: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {
        Text(
            text = movieName,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = White
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = networkName,
            color = White,
            fontSize = 16.sp,
        )

        Text(
            modifier = Modifier.padding(vertical = 6.dp),
            text = rating,
            color = White,
            fontSize = 16.sp,
        )
    }
}

/**
 * Funcion composable que nos direcciona a un web Browser
 * @param officialSite Link en donde se alberga el show en la web
 */
@Composable
private fun GoTo(officialSite: String) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(officialSite))
            context.startActivity(intent)
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = LapisLazuli,
            contentColor = White,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
    ) {
        Text(text = "Visitar Sitio", fontSize = 18.sp)
    }
}

/**
 * Componente interno para usar en las descripciones
 * @param summary String con Descripcion de la pelicula
 * @param genres String con los generos que tiene la pelicla
 * @param time String con el tiempo que tiene la pelicla
 * @param days String con los dias que tiene la pelicla
 */
@Composable
private fun MovieDescription(
    summary: String,
    genres: String,
    time: String,
    days: String
) {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sinopsis: ",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = White
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = summary,
            fontSize = 16.sp,
            color = White
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 6.dp),
        ) {
            Text(
                text = "Generos: ",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = White
            )
            Text(
                text = genres,
                fontSize = 16.sp,
                color = White
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 6.dp),
        ) {
            Text(
                text = "Horario: ",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = White
            )
            Text(
                text = "$time | $days",
                fontSize = 16.sp,
                color = White
            )
        }
    }
}


/**
 * Funcion composable Para crear el componente que contiene a los talentos
 * @param talents Lista mutable con los talentos que participaron en la pelicula
 */
@Composable
private fun TalentsComponent(talents: MutableList<TalentModel>?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {
        Text(
            text = "Talentos: ",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = White
        )
        LazyRow {
            talents?.let {
                items(it) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 12.dp, bottom = 12.dp)
                    ) {
                        Talent(url = it.talentImage, name = it.talentName)
                    }
                }
            }
        }
    }
}


/**
 * Funcion composable del item para talentos
 * @param url Imagen medium para mostrar en UI
 * @param name Nombre del artista
 */
@Composable
private fun Talent(url: String, name: String) {
    Column(
        modifier = Modifier
            .padding(bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = url),
            contentDescription = name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(120.dp, 140.dp)
                .padding(top = 18.dp)
        )
        Text(
            text = name,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 7.dp)
                .width(115.dp),
            color = White
        )

    }
}
