package com.example.football_club


import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.room.Room
import org.json.JSONObject


@Composable
fun AddLeagues(
    modifier: Modifier,
    navController: NavHostController,
) {
    // Capture and store screen orientation
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    // Elements
    val homeScreenButton = "Go back to home screen"
    val tickImg = painterResource(id = R.drawable.greentick)
    val leaguesData = readJsonLeagues(LocalContext.current) // Read JSON file

    // Database instance
    val db = Room.databaseBuilder(
        LocalContext.current,
        AppDatabase::class.java,
        "football_league"
    ).build()
    val leagueDao = db.leagueDao()

    // Add Leagues to DB
    LaunchedEffect(leaguesData){
        leagueDao.insertLeague(leaguesData)
    }

    // If the screen is in portrait mode
    if(isPortrait){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),

                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(400)),
                text = "The leagues on football_leagues.json have been added to the database successfully."
            )

            Spacer(modifier = Modifier.height(20.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = tickImg, contentDescription = null
            )

            Spacer(modifier = Modifier.height(20.dp))
            AppButton(
                label = homeScreenButton,
                onClick = { navController.navigate("HomeScreen"); },
                enabled = true,
                modifier = modifier
            )
        }
    }
    // If the screen is in landscape mode
    else
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),

                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(400)),
                text = "The leagues on football_leagues.json have been added to the database successfully."
            )
            Spacer(modifier = Modifier.height(20.dp))

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = tickImg, contentDescription = null
            )
            Spacer(modifier = Modifier.height(20.dp))

            AppButton(
                label = homeScreenButton,
                onClick = { navController.navigate("HomeScreen"); },
                enabled = true,
                modifier = modifier
            )
        }
}

// Read Json file of football leagues
fun readJsonLeagues(context: android.content.Context): List<League> {
    val leagues = mutableListOf<League>() // List of leagues

    // Read JSON file
    try {
        val jsonString = context.resources.openRawResource(R.raw.football_leagues).bufferedReader().use { it.readText() }
        val json = JSONObject(jsonString)
        val leaguesArray = json.getJSONArray("leagues")

        // Add leagues to list from JSON file
        for (i in 0 until leaguesArray.length()) {
            val leagueObj = leaguesArray.getJSONObject(i)
            val idLeague = leagueObj.getInt("idLeague")
            val strLeague = leagueObj.getString("strLeague")
            val strSport = leagueObj.getString("strSport")
            val strLeagueAlternate = leagueObj.getString("strLeagueAlternate")
            leagues.add(
                League(
                    idLeague,
                    strLeague,
                    strSport,
                    strLeagueAlternate
                )
            )
        }
    } catch (e:Exception) {
        Log.d("Error","Error occurred while reading JSON file.")
    }
    return leagues
}

/*
* References:
* Patel, K. (2024). Handling Screen Orientation Changes in Jetpack Compose. LinkeIn. Available from https://www.linkedin.com/pulse/handling-screen-orientation-changes-jetpack-compose-kaleem-patel-iztuf/ [Accessed 4 May 2024].
*/