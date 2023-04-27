package com.example.tvmazecompose.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Erik Hernandez on 24/04/2023.
 */
@Entity(tableName = "movies")
data class MovieModel(
    @ColumnInfo(name = "image") val image: String = "",
    @ColumnInfo(name = "movieName") val movieName: String = "",
    @ColumnInfo(name = "networkName") val networkName: String = "",
    @ColumnInfo(name = "time") val time: String = "",
    @ColumnInfo(name = "days") val days: String = "",
    @ColumnInfo(name = "rating") val rating: Double = 0.0,
    @ColumnInfo(name = "url") val url: String = "",
    @ColumnInfo(name = "summary") val summary: String = "",
    @ColumnInfo(name = "genres") val genres: String = "",
    @ColumnInfo(name = "airtime") val airtime: String = "",
    @PrimaryKey(autoGenerate = false) val id: Int = 0
)
