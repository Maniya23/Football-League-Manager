package com.example.football_club

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

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

//        // Get League by ID
//        @Query("SELECT * FROM leagues WHERE idLeague = :id")
//        fun getLeagueById(id: String): LiveData<League>
//
//        // Search Leagues by Name
//        @Query("SELECT * FROM leagues WHERE strLeague LIKE '%' || :name || '%'")
//        fun searchLeaguesByName(name: String): LiveData<List<League>>
}