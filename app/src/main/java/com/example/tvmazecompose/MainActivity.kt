package com.example.tvmazecompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tvmazecompose.core.navigation.Routes
import com.example.tvmazecompose.ui.screens.HomeScreen
import com.example.tvmazecompose.ui.screens.MovieDetailScreen
import com.example.tvmazecompose.ui.screens.StartScreen
import com.example.tvmazecompose.ui.theme.Aureolin
import com.example.tvmazecompose.ui.theme.OnyxBackground
import com.example.tvmazecompose.ui.theme.TvMazeComposeTheme
import com.example.tvmazecompose.ui.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var viewModel: MoviesViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        setContent {
            TvMazeComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = OnyxBackground
                ) {
                    val isLoading by viewModel.isLoading.observeAsState(false)
                    if (isLoading) {
                        Loading()
                    }
                    val navHostController = rememberNavController()
                    NavHost(
                        navController = navHostController,
                        startDestination = Routes.StartScreen.routes
                    ) {
                        composable(route = Routes.StartScreen.routes) {
                            StartScreen(viewModel, navHostController)
                        }
                        composable(route = Routes.HomeScreen.routes) {
                            HomeScreen(viewModel, navHostController)
                        }
                        composable(route = Routes.MovieDetailScreen.routes) {
                            MovieDetailScreen(viewModel)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Loading() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = Aureolin
        )
    }
}
