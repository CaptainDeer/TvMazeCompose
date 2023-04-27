package com.example.tvmazecompose.data.model

import com.squareup.moshi.Json

/**
 * Created by Erik Hernandez on 26/04/2023.
 */
data class Talent(
    @field:Json(name = "person") val person: Person,
    @field:Json(name = "character") val character: Character,
    @field:Json(name = "self") val self: Boolean,
    @field:Json(name = "voice") val voice: Boolean,
)
