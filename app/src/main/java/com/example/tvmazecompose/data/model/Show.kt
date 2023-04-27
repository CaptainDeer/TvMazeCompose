package com.example.tvmazecompose.data.model

import com.squareup.moshi.Json

/**
 * Created by Erik Hernandez on 24/04/2023.
 */
data class Show(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "language") val language: String?,
    @field:Json(name = "genres") val genres: List<String>?,
    @field:Json(name = "status") val status: String?,
    @field:Json(name = "runtime") val runtime: Int?,
    @field:Json(name = "averageRuntime") val averageRuntime: Int?,
    @field:Json(name = "premiered") val premiered: String?,
    @field:Json(name = "ended") val ended: String?,
    @field:Json(name = "officialSite") val officialSite: String?,
    @field:Json(name = "schedule") val schedule: Schedule,
    @field:Json(name = "rating") val rating: Rating,
    @field:Json(name = "weight") val weight: Int?,
    @field:Json(name = "network") val network: Network?,
    @field:Json(name = "webChannel") val webChannel: Any?,
    @field:Json(name = "dvdCountry") val dvdCountry: String?,
    @field:Json(name = "externals") val externals: Externals,
    @field:Json(name = "image") val image: MovieImage?,
    @field:Json(name = "summary") val summary: String?,
    @field:Json(name = "updated") val updated: Int?,
    @field:Json(name = "_links") val _links: Links,
)
