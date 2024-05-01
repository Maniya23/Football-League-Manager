package com.example.football_club

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
//            val db = Room.databaseBuilder(
//                applicationContext,
//                AppDatabase::class.java,
//                "football_club"
//            ).build()

            fun initializeDb(){}

            //App Navigation
            NavHost(navController = navController, startDestination = "HomeScreen") {
                composable("HomeScreen") { HomeScreen(
                    modifier = Modifier,
                    navController,)
                }

                composable("AddLeagues") {
                    AddLeagues(
                        modifier = Modifier,
                        navController,
                    )
                }
                composable("SearchClubByLeague") {
                    SearchClubByLeague(
                        modifier = Modifier,

                    )
                }
                composable("SearchClubs") {
                    SearchClubs(
                        modifier = Modifier,
                    )
                }
                composable("SearchAllClubs") {
                    SearchAllClubs(
                        modifier = Modifier,
                    )
                }
            }
        }
    }
}