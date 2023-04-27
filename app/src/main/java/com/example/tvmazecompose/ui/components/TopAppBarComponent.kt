package com.example.tvmazecompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tvmazecompose.ui.theme.LapisLazuli
import com.example.tvmazecompose.ui.theme.White
import com.example.tvmazecompose.ui.viewmodels.MoviesViewModel

/**
 * Created by Erik Hernandez on 27/04/2023.
 */

/**
 * Funcion composable que contiene el componente TopAppBar de la app
 * @param viewModel MoviesViewModel para poder mostrar los datos
 */
@Composable
internal fun TopAppBarComponent(
    viewModel: MoviesViewModel = hiltViewModel(),
) {
    val titles by viewModel.topAppBarTitle.observeAsState()
    TopAppBar(
        backgroundColor = LapisLazuli
    ) {
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.high,
            LocalTextStyle provides MaterialTheme.typography.h6
        ) {
            Text(
                text = titles.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 20.dp),
                color = White
            )
        }
    }
}
