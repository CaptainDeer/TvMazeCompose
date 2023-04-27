package com.example.tvmazecompose.data.model

import com.squareup.moshi.Json

/**
 * Created by Erik Hernandez on 25/04/2023.
 */
data class MovieImage(
    @field:Json(name = "medium") val medium: String = "",
    @field:Json(name = "original") val original: String = ""
)
