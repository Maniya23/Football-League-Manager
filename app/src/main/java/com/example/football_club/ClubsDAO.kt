package com.example.football_club

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// Define the ClubsDAO interface
@Dao
interface ClubsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClubs(clubs: List<Clubs>)

    @Query("SELECT * FROM leagueClubs")
    fun getAllClubs(): List<Clubs>

    @Query("SELECT Name,strLeague,strTeamLogo From leagueClubs WHERE name LIKE '%' || :name || '%' or strLeague LIKE '%' || :name || '%'")
    fun searchClubsByName(name: String): List<Clubs>
}