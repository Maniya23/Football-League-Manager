package com.example.football_club

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    // Start of display
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
                text = "Football Clubs",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight(600))
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            label = "Add Leagues to DB",
            onClick = { navController.navigate("AddLeagues") },
            enabled = true,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            label = "Search for Clubs By League",
            onClick = { navController.navigate("SearchClubByLeague") },
            enabled = true,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            label = "Search for Clubs",
            onClick = { navController.navigate("SearchClubs") },
            enabled = true,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(16.dp))
        AppButton(
            label = "Search All Clubs",
            onClick = { navController.navigate("SearchAllClubs") },
            enabled = true,
            modifier = Modifier
        )
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