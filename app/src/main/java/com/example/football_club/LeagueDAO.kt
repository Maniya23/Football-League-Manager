package com.example.football_club

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// Define the LeagueDAO interface
@Dao
interface LeagueDAO {
    // Add Leagues to DB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeague(leagues: List<League>)

    // Get Leagues from DB
    @Query("SELECT * FROM leagues")
    suspend fun getAllLeagues(): List<League>

    @Query("SELECT * FROM leagues WHERE strLeague LIKE '%' || :name || '%'")
    suspend fun searchLeaguesByName(name: String): List<League>
}