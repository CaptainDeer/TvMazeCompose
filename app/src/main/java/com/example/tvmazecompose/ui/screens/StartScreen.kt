package com.example.tvmazecompose.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tvmazecompose.ui.theme.LapisLazuli
import com.example.tvmazecompose.ui.theme.Orange
import com.example.tvmazecompose.ui.theme.White
import com.example.tvmazecompose.ui.viewmodels.MoviesViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Created by Erik Hernandez on 26/04/2023.
 */


/**
 * Pantalla principal que se vera recien se abre la app
 * @param viewModel MoviesViewModel para poder mostrar los datos e interactuar con servicios
 * @param navHostController necesario para poder redireccionar a otras rutas
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StartScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    viewModel.delete()
    val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.now().format(dateFormat)
    StartComponents(viewModel = viewModel, date = date) {
        navHostController.navigate(it)
    }
}


/**
 * Inicializa componentes y da formato a las pantallas
 * @param viewModel MoviesViewModel para poder mostrar los datos
 * @param date dia actual, se requeire para ciertas consultas
 * @param navigation String con la ruta de direccion
 */
@Composable
private fun StartComponents(
    viewModel: MoviesViewModel,
    date: String,
    navigation: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Greeting(viewModel, date, navigation)
    }
}

/**
 * Saludo es la pantalla en donde muestro la bienvenida
 * @param viewModel para iniciar la app
 * @param date dia actual, se requeire para ciertas consultas
 * @param navigation String con la ruta de direccion
 */
@Composable
private fun Greeting(
    viewModel: MoviesViewModel,
    date: String,
    navigation: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Bienvenido",
            color = Orange,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .padding(vertical = 30.dp)
        )
        Text(
            text = "Esta app se desarrolla con Jetpack Compose, trabajando al mismo tiempo con un patron de diseño MVVM y siguiendo los patrones de Clean Architecture.\n" +
                    "\n" +
                    "-Core: Puedes encontrar recursos compartidos al rededor de toda la aplicacion\n" +
                    "-Data: Todo lo relacionado con llamadas al servidor (Corrutinas, RoomDatabase)\n" +
                    "-Domain: Modelos necesarios para el buen funcionamiento de mi app\n" +
                    "-UI: Aqui se encuentra el ViewModel ya que es la parte que interactua con el UI. Tambien esta los archivos con composable.",
            color = White,
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
        )
        StartComponent(viewModel = viewModel, date = date, navigation = navigation)
    }
}


/**
 * Creacion de un boton con direccionamiento
 * @param viewModel MoviesViewModel para poder mostrar los datos
 * @param date dia actual, se requeire para ciertas consultas
 * @param navigation String con la ruta de direccion
 */
@Composable
private fun StartComponent(
    viewModel: MoviesViewModel,
    date: String,
    navigation: (String) -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 100.dp, vertical = 32.dp),
        onClick = {
            viewModel.getMovies(date, navigation)
        },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = LapisLazuli,
            contentColor = White,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = "Iniciar",
            fontSize = 18.sp
        )
    }
}
