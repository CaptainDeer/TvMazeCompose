package com.example.tvmazecompose.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tvmazecompose.ui.theme.Aureolin
import com.example.tvmazecompose.ui.theme.Onyx
import com.example.tvmazecompose.ui.theme.White
import com.example.tvmazecompose.ui.viewmodels.MoviesViewModel

/**
 * Created by Erik Hernandez on 27/04/2023.
 */

/**
 * Funcion composable que contiene el componente de busqueda
 * @param viewModel MoviesViewModel para poder mostrar los datos
 */
@Composable
fun SearchComponent(
    viewModel: MoviesViewModel,
) {
    val text by viewModel.search.observeAsState()
    val search = remember {
        viewModel.search
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Onyx),
            value = text ?: "",
            onValueChange = { search.postValue(it) },
            placeholder = {
                Text(
                    modifier = Modifier,
                    text = "Search",
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    color = White
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = White,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        text?.let { SearchButtonComponent(it, viewModel) }
    }
}

/**
 * Funcion composable que contiene el boton de buscar
 * @param search String que nos dice que palabra se va a buscar
 * @param viewModel MoviesViewModel para el click
 */
@Composable
private fun SearchButtonComponent(
    search: String,
    viewModel: MoviesViewModel,
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, start = 12.dp),
        onClick = {
            viewModel.searchMovie(search)
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Aureolin,
            contentColor = Color.Black,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Icono de búsqueda",
            tint = Color.Black
        )
    }
}
