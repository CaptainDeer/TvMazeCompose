package com.example.tvmazecompose.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Erik Hernandez on 26/04/2023.
 */
@Entity(tableName = "talents")
data class TalentModel(
    @ColumnInfo(name = "talentImage") val talentImage: String = "",
    @ColumnInfo(name = "talentName") val talentName: String = "",
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
