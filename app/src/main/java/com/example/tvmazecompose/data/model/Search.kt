package com.example.tvmazecompose.data.model

import com.squareup.moshi.Json

/**
 * Created by Erik Hernandez on 27/04/2023.
 */
data class Search(
    @field:Json(name = "score") val score: Double,
    @field:Json(name = "show") val show: Show,
)
