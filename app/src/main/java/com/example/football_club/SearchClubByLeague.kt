package com.example.football_club


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun SearchClubByLeague(
    modifier: Modifier,
) {
    var clubsList by remember { mutableStateOf(listOf<Clubs>()) }
    var leagueName by remember { mutableStateOf("") }
    var enableLeagueName by remember { mutableStateOf(true) }
    var enableSaveClubs by remember { mutableStateOf(false) }
    var showClubs by remember { mutableStateOf(false) }
    var alertSave by remember { mutableStateOf(false) }

    val db = Room.databaseBuilder(
        LocalContext.current,
        AppDatabase::class.java,
        "football_league"
    ).build()
    val clubsDao = db.clubsDao()

    val scope = rememberCoroutineScope()

//    LaunchedEffect(Unit){
//        withContext(Dispatchers.IO){
//            clubsDao.insertClubs(listOf(Clubs(133604,"Arsenal","ARS","Arsenal Football Club, AFC, Arsenal FC",1892,"English Premier League",4328,"Emirates Stadium","Gunners, Gooners","https://www.thesportsdb.com/images/w1anwa1588432105.jpg","Holloway, London, England",60338,"www.arsenal.com","https://www.thesportsdb.com/images/f8w08g1687181718.png","https://www.thesportsdb.com/images/q2mxlz1512644512.png")))
//        }
//    }


    // Search Club by League
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
    )
    {
        TextField(
            value = leagueName,
            onValueChange = { leagueName = it },
            label = { Text("Enter the league name:") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enableLeagueName,
        )

        Spacer(modifier = Modifier.height(20.dp))
        AppButton(
            label = "Retrieve Clubs",
            onClick = {
                scope.launch {
                    clubsList = searchClubsInLeague(leagueName)
                }
                enableSaveClubs = true
                showClubs = true
            },
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        AppButton(
            label = "Save clubs to Database",
            onClick = {
                scope.launch {
                    withContext(Dispatchers.IO){
                        clubsDao.insertClubs(clubsList)
                    }
                }

                alertSave = true
            },
            enabled = enableSaveClubs,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        if (showClubs){
            LazyColumn{
                items(clubsList.size){ index ->
                    Text(text = toString(clubsList[index]))
                }
            }
        }

        if(alertSave){
            AlertDialog(
                onDismissRequest = { alertSave = false },
                text = { Text(
                    text = "Successfully saved clubs to database!",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        color = Color.Green
                    ),
                )
                },
                confirmButton = {
                    Button(onClick = { alertSave = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

// function to retrieve clubs in a league
suspend fun searchClubsInLeague(leagueName: String): List<Clubs>{
    val clubsList = mutableListOf<Clubs>()
    val urlString = "https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?l=$leagueName"
    val url = URL(urlString)
    val con: HttpURLConnection = url.openConnection() as HttpURLConnection

//    Log.d("SearchClubs", "Hello")
    withContext(Dispatchers.IO){
        con.requestMethod = "GET"
        val jsonClubsResponse = con.inputStream.bufferedReader().use { it.readText() }
        val jsonClubs = JSONObject(jsonClubsResponse)
//        Log.d("SearchClubs", "Hello2")
        val clubsArray = jsonClubs.getJSONArray("teams")

        for (i in 0 until clubsArray.length()){
            val clubObj = clubsArray.getJSONObject(i)
            val idTeam = clubObj.getInt("idTeam")
            val name = clubObj.getString("strTeam")
            val strTeamShort = clubObj.getString("strTeamShort")
            val strAlternate = clubObj.getString("strAlternate")
            val intFormedYear = clubObj.getInt("intFormedYear")
            val strLeague = clubObj.getString("strLeague")
            val idLeague = clubObj.getInt("idLeague")
            val strStadium = clubObj.getString("strStadium")
            val strKeywords =  clubObj.getString("strKeywords")
            val strStadiumThumb = clubObj.getString("strStadiumThumb")
            val strStadiumLocation = clubObj.getString("strStadiumLocation")
            val intStadiumCapacity = clubObj.getInt("intStadiumCapacity")
            val strWebsite = clubObj.getString("strWebsite")
            val strTeamJersey = clubObj.getString("strTeamJersey")
            val strTeamLogo = clubObj.getString("strTeamLogo")

            clubsList.add(Clubs
                (idTeam, name, strTeamShort, strAlternate, intFormedYear, strLeague, idLeague, strStadium, strKeywords, strStadiumThumb, strStadiumLocation, intStadiumCapacity, strWebsite, strTeamJersey, strTeamLogo)
            )
        }
    }
    return clubsList
}

@Override
fun toString(club: Clubs): String {

    return """
        Team ID: ${club.idTeam}
        Name: ${club.Name}
        Short Name: ${club.strTeamShort}
        Alternate Name: ${club.strAlternate}
        Formed Year: ${club.intFormedYear}
        League: ${club.strLeague}
        League ID: ${club.idLeague}
        Stadium: ${club.strStadium}
        Keywords: ${club.strKeywords}
        Stadium Thumb: ${club.strStadiumThumb}
        Stadium Location: ${club.strStadiumLocation}
        Stadium Capacity: ${club.intStadiumCapacity}
        Website: ${club.strWebsite}
        Team Jersey: ${club.strTeamJersey}
        Team Logo: ${club.strTeamLogo}
        
    """
}


@Preview
@Composable
fun PreviewSearchClubByLeague() {
    SearchClubByLeague(
        modifier = Modifier,
    )
}