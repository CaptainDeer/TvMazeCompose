package com.example.tvmazecompose.data.model

import com.squareup.moshi.Json

/**
 * Created by Erik Hernandez on 24/04/2023.
 */
data class Schedule(
    @field:Json(name = "time") val time: String,
    @field:Json(name = "days") val days: List<String>?,
)
