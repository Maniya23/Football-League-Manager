package com.example.football_club

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leagueClubs")
data class Clubs(
    @PrimaryKey
    val idTeam: Int?,
    val Name: String?,
    val strTeamShort: String?,
    val strAlternate: String?,
    val intFormedYear: Int?,
    val strLeague: String?,
    val idLeague: Int?,
    val strStadium: String?,
    val strKeywords: String?,
    val strStadiumThumb: String?,
    val strStadiumLocation: String?,
    val intStadiumCapacity: Int?,
    val strWebsite: String?,
    val strTeamJersey: String?,
    val strTeamLogo: String?,
)