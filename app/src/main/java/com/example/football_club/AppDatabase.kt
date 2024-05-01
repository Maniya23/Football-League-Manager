package com.example.football_club

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [League::class, Clubs::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun leagueDao(): LeagueDAO
    abstract fun clubsDao(): ClubsDAO
}