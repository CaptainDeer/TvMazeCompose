package com.example.tvmazecompose.data.model

import com.squareup.moshi.Json

/**
 * Created by Erik Hernandez on 26/04/2023.
 */
data class Character(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "image") val image: Image,
    @field:Json(name = "_links") val _links: Links
)
