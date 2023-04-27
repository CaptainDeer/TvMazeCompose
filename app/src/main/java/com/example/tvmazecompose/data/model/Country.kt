package com.example.tvmazecompose.data.model

import com.squareup.moshi.Json

/**
 * Created by Erik Hernandez on 24/04/2023.
 */
data class Country(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "code") val code: String,
    @field:Json(name = "timezone") val timezone: String,
    @field:Json(name = "officialSite") val officialSite: String?,
)
