package com.example.football_club

import androidx.room.Entity
import androidx.room.PrimaryKey

// Define the League data class
@Entity (tableName = "leagues")
data class League(
    @PrimaryKey
    val idLeague: Int?,
    val strLeague: String?,
    val strSport: String?,
    val strLeagueAlternate: String?,
)
