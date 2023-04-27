package com.example.tvmazecompose.data.model

import com.squareup.moshi.Json

/**
 * Created by Erik Hernandez on 26/04/2023.
 */
data class Person(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "country") val country: Country,
    @field:Json(name = "birthday") val birthday: String,
    @field:Json(name = "deathday") val deathday: String?,
    @field:Json(name = "gender") val gender: String,
    @field:Json(name = "image") val image: Image?,
    @field:Json(name = "updated") val updated: Int,
    @field:Json(name = "_links") val _links: Links
)
