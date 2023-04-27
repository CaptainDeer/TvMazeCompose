package com.example.tvmazecompose.data.model

import com.squareup.moshi.Json

/**
 * Created by Erik Hernandez on 24/04/2023.
 */
data class Network(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "country") val country: Country?,
    @field:Json(name = "officialSite") val officialSite: String?,
)
