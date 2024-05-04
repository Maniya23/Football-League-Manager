package com.example.football_club

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    // Capture and store screen orientation
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    // Home screen elements
    val heading = "Football Manager"
    val addLeaguesButton = "Add Leagues to DB"
    val clubsByLeagueButton = "Search for Clubs By League"
    val searchClubsButton = "Search for Clubs"
    val searchAllClubsButton = "Search All Clubs"

    // If the screen is in portrait mode
    if (isPortrait){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFD6EAF8)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = heading,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight(600))
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            AppButton(
                label = addLeaguesButton,
                onClick = { navController.navigate("AddLeagues") },
                enabled = true,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(16.dp))

            AppButton(
                label = clubsByLeagueButton,
                onClick = { navController.navigate("SearchClubByLeague") },
                enabled = true,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(16.dp))

            AppButton(
                label = searchClubsButton,
                onClick = { navController.navigate("SearchClubs") },
                enabled = true,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(16.dp))

            AppButton(
                label = searchAllClubsButton,
                onClick = { navController.navigate("SearchAllClubs") },
                enabled = true,
                modifier = Modifier
            )
        }
    }
    // If the screen is in landscape mode
    else {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFD6EAF8)),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = heading,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight(600))
                )
            }
            Spacer(modifier = Modifier.height(40.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ){
                AppButton(
                    label = addLeaguesButton,
                    onClick = { navController.navigate("AddLeagues") },
                    enabled = true,
                    modifier = Modifier
                        .width(350.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                AppButton(
                    label = clubsByLeagueButton,
                    onClick = { navController.navigate("SearchClubByLeague") },
                    enabled = true,
                    modifier = Modifier
                        .width(350.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ){
                AppButton(
                    label = searchClubsButton,
                    onClick = { navController.navigate("SearchClubs") },
                    enabled = true,
                    modifier = Modifier
                        .width(350.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                AppButton(
                    label = searchAllClubsButton,
                    onClick = { navController.navigate("SearchAllClubs") },
                    enabled = true,
                    modifier = Modifier
                        .width(350.dp)
                )
            }
        }
    }
}


@Preview(name = "HomeScreen")
@Composable
private fun PreviewHomeScreen() {
    val navController = rememberNavController()
    HomeScreen(
        modifier = Modifier,
        navController = navController,
    )
}