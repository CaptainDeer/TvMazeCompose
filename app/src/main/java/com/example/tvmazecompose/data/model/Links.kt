package com.example.tvmazecompose.data.model

import com.squareup.moshi.Json

/**
 * Created by Erik Hernandez on 24/04/2023.
 */
data class Links(
    @field:Json(name = "self") val self: Href?,
    @field:Json(name = "previousepisode") val previousepisode: Href?,
    @field:Json(name = "nextepisode") val nextepisode: Href?,
    @field:Json(name = "show") val show: Href?
)
