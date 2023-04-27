package com.example.tvmazecompose.data.model

import com.squareup.moshi.Json

/**
 * Created by Erik Hernandez on 24/04/2023.
 */
data class Image(
    @field:Json(name = "medium") val medium: String?,
    @field:Json(name = "original") val original: String,
)
