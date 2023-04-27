package com.example.tvmazecompose.data.model

import com.example.tvmazecompose.domain.model.MovieDetailModel
import com.example.tvmazecompose.domain.model.MovieModel
import com.example.tvmazecompose.domain.model.TalentModel
import com.squareup.moshi.Json

/**
 * Created by Erik Hernandez on 24/04/2023.
 */
data class Movie(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "season") val season: Int?,
    @field:Json(name = "number") val number: Int?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "airdate") val airdate: String?,
    @field:Json(name = "airtime") val airtime: String?,
    @field:Json(name = "airstamp") val airstamp: String?,
    @field:Json(name = "runtime") val runtime: String?,
    @field:Json(name = "rating") val rating: Rating,
    @field:Json(name = "image") val image: Any?,
    @field:Json(name = "summary") val summary: String?,
    @field:Json(name = "show") val show: Show,
    @field:Json(name = "_links") val _links: Links
)

fun Movie.toDomain(): MovieModel = MovieModel(
    image = show.image?.medium
        ?: "https://upload.wikimedia.org/wikipedia/commons/3/3f/Placeholder_view_vector.svg",
    movieName = show.name.toString(),
    networkName = show.network?.name ?: "N/A",
    time = show.schedule.time,
    days = show.schedule.days.toString(),
    rating = show.rating.average ?: 0.0,
    url = url,
    summary = show.summary ?: "",
    genres = show.genres.toString(),
    airtime = airtime ?: "",
    id = show.id
)

fun Show.toDomain(): MovieDetailModel = MovieDetailModel(
    image = image?.medium
        ?: "https://upload.wikimedia.org/wikipedia/commons/3/3f/Placeholder_view_vector.svg",
    movieName = name ?: "",
    networkName = network?.name ?: "",
    rating = rating.average.toString(),
    officialSite = officialSite ?: "",
    summary = summary ?: "",
    genres = genres.toString(),
    time = schedule.time,
    days = schedule.days.toString(),
    id = id
)

fun Talent.toDomain(): TalentModel = TalentModel(
    talentImage = person.image?.medium
        ?: "https://upload.wikimedia.org/wikipedia/commons/3/3f/Placeholder_view_vector.svg",
    talentName = person.name,
)

fun Search.toDomain(): MovieModel = MovieModel(
    image = show.image?.medium
        ?: "https://upload.wikimedia.org/wikipedia/commons/3/3f/Placeholder_view_vector.svg",
    movieName = show.name.toString(),
    networkName = show.network?.name ?: "N/A",
    time = show.schedule.time,
    days = show.schedule.days.toString(),
    rating = show.rating.average ?: 0.0,
    url = show.url,
    summary = show.summary ?: "",
    genres = show.genres.toString(),
    airtime = show.schedule.days.toString(),
    id = show.id
)
