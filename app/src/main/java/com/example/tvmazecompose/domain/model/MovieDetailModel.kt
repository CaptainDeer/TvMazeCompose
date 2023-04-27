package com.example.tvmazecompose.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Erik Hernandez on 26/04/2023.
 */
@Entity(tableName = "movie_detail")
data class MovieDetailModel(
    @ColumnInfo(name = "image") val image: String = "",
    @ColumnInfo(name = "movieName") val movieName: String = "",
    @ColumnInfo(name = "networkName") val networkName: String = "",
    @ColumnInfo(name = "rating") val rating: String = "",
    @ColumnInfo(name = "officialSite") val officialSite: String = "",
    @ColumnInfo(name = "summary") val summary: String = "",
    @ColumnInfo(name = "genres") val genres: String = "",
    @ColumnInfo(name = "time") val time: String = "",
    @ColumnInfo(name = "days") val days: String = "",
    @PrimaryKey(autoGenerate = false) val id: Int = 0
)
