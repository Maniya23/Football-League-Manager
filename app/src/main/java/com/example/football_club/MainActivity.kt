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

            //App Navigation
            // Home screen (Default Screen)
            NavHost(navController = navController, startDestination = "HomeScreen") {
                composable("HomeScreen") { HomeScreen(
                    modifier = Modifier,
                    navController,)
                }

                // Add Leagues screen
                composable("AddLeagues") {
                    AddLeagues(
                        modifier = Modifier,
                        navController,
                    )
                }

                // Search Club by League screen
                composable("SearchClubByLeague") {
                    SearchClubByLeague(
                        modifier = Modifier,

                    )
                }

                // Search Clubs screen
                composable("SearchClubs") {
                    SearchClubs(
                        modifier = Modifier,
                    )
                }

                // Search All Clubs screen (Additional button)
                composable("SearchAllClubs") {
                    SearchAllClubs(
                        modifier = Modifier,
                    )
                }
            }
        }
    }
}