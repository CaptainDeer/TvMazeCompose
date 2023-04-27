package com.example.tvmazecompose.data.model

import com.squareup.moshi.Json

/**
 * Created by Erik Hernandez on 24/04/2023.
 */
data class Externals(
    @field:Json(name = "tvrage") val tvrage: Int?,
    @field:Json(name = "thetvdb") val thetvdb: Int?,
    @field:Json(name = "imdb") val imdb: String?
)
