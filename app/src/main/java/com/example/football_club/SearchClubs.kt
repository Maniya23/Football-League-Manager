package com.example.football_club

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SearchClubs(
    modifier: Modifier,
) {
    // Capture and store screen orientation
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    // Database instance
    val db = Room.databaseBuilder(
        LocalContext.current,
        AppDatabase::class.java,
        "football_league"
    ).build()
    val clubsDao = db.clubsDao()

    // Coroutine scope
    val scope = rememberCoroutineScope()

    // Elements
    var clubName by remember { mutableStateOf("") }
    var clubs by remember { mutableStateOf(listOf<Clubs>()) }
    var showClubs by remember { mutableStateOf(false) }

    // If the screen is in portrait mode
    if (isPortrait){
        Column (
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
        )
        {
            Row {
                TextField(
                    value = clubName,
                    onValueChange = { clubName = it.lowercase() },
                    label = { Text("Enter the club name:") },
                    modifier = Modifier,
                    enabled = true,
                )
                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    shape = CircleShape,
                    modifier = Modifier.size(40.dp),
                    contentPadding = PaddingValues(0.dp),
                    onClick = {
                        scope.launch {
                            withContext(Dispatchers.IO) {
                                // Search for club
                                clubs = clubsDao.searchClubsByName(clubName).toMutableList()
                                showClubs = true
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            // Display the clubs
            if (showClubs) {
                LazyColumn {
                    items(clubs.size) { index ->
                        ViewClub(clubs[index])
                    }
                }
            }
        }
    }
    // If the screen is in landscape mode
    else {
        Row (
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
        )
        {
            Column {
                Row {
                    TextField(
                        value = clubName,
                        onValueChange = { clubName = it.lowercase() },
                        label = { Text("Enter the club name:") },
                        modifier = Modifier,
                        enabled = true,
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        shape = CircleShape,
                        modifier = Modifier.size(40.dp),
                        contentPadding = PaddingValues(0.dp),
                        onClick = {
                            scope.launch {
                                withContext(Dispatchers.IO) {
                                    // Search for club
                                    clubs = clubsDao.searchClubsByName(clubName).toMutableList()
                                    showClubs = true
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(60.dp))

            // Display the clubs
            if (showClubs) {
                LazyColumn {
                    items(clubs.size) { index ->
                        ViewClub(clubs[index])
                    }
                }
            }
        }
    }
}


// Display the clubs and logos
@Composable
fun ViewClub(club: Clubs){
    Column {
        club.Name?.let { Text(
            text = "Name of Club : $it",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(600)),
        ) }
        club.strLeague?.let { Text(text = "Name of League : $it") }
        AsyncImage(
            model = club.strTeamLogo,
            contentDescription = "Team jersey",
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}


@Preview
@Composable
fun SearchClubsPreview() {
    SearchClubs(modifier = Modifier)
}

/*
* References:
* Gomez, O. D. L. H. (2023). How to load and display an image from a URL using Jetpack Compose and Kotlin. Delasign. Available from https://www.delasign.com/blog/android-studio-jetpack-compose-kotlin-image-from-url/ [Accessed 1 May 2024].
* Patel, K. (2024). Handling Screen Orientation Changes in Jetpack Compose. LinkeIn. Available from https://www.linkedin.com/pulse/handling-screen-orientation-changes-jetpack-compose-kaleem-patel-iztuf/ [Accessed 4 May 2024].
*/