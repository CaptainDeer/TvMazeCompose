package com.example.tvmazecompose.core.navigation

/**
 * Created by Erik Hernandez on 26/04/2023.
 */
sealed class Routes(val routes: String) {
    object StartScreen : Routes("startScreen")
    object HomeScreen : Routes("homeScreen")
    object MovieDetailScreen : Routes("movieDetailScreen")
}