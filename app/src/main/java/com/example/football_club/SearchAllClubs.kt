//package com.example.football_club
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.Button
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import java.net.URL
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import org.json.JSONObject
//import java.net.HttpURLConnection
//
////data class TeamEquipmentList(
////    val teamList: List<String>,
////    val equipmentList: List<String>
////)
//
//@Composable
//fun SearchAllClubs(
//    modifier: Modifier,
//){
//    var name by remember { mutableStateOf("") }
//    val scope = rememberCoroutineScope()
////    var teamEquipment: TeamEquipmentList? = null
//
//    Column {
//        Row {
//            TextField(
//                value = name,
//                onValueChange = { name = it.lowercase() },
//                label = { Text("Enter the club :") },
//                modifier = Modifier,
//                enabled = true,
//            )
//            Spacer(modifier = Modifier.width(10.dp))
//            Button(
//                shape = CircleShape,
//                modifier = Modifier.size(40.dp),
//                contentPadding = PaddingValues(0.dp),
//                onClick = {
//                        ReadAPI(name,scope)
//                }
//            ) {
//                // Inner content including an icon and a text label
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = "Search",
//                    modifier = Modifier.size(20.dp)
//                )
//            }
//        }
////        val teams = teamEquipment?.teamList
////        val teamEquipments = teamEquipment?.equipmentList
////
////        if (teams != null){
////            for (i in teams.indices){
////                Text("Team: ${teams[i]}")
////                for (j in teamEquipments!!.indices){
////                    Text("Equipment: ${teamEquipments[j]}")
////                }
////            }
////        }
//    }
//
//}
//
//@Composable
//fun ReadAPI(name: String, scope: CoroutineScope) {
//
//    val equipmentList = mutableListOf<String>()
//    val teamList = mutableListOf<String>()
//    val urlStringSearch = "www.thesportsdb.com/api/v1/json/3/searchteams.php?t=$name"
//    var urlStringLookups: String
//    val urlSearch = URL(urlStringSearch)
//    val conSearch: HttpURLConnection = urlSearch.openConnection() as HttpURLConnection
//
//    scope.launch {
//        withContext(Dispatchers.IO){
//            conSearch.requestMethod = "GET"
//            val jsonResponseTeam = conSearch.inputStream.bufferedReader().use { it.readText() }
//            val jsonTeam = JSONObject(jsonResponseTeam)
//            val teamsArray = jsonTeam.getJSONArray("teams")
//
//            for (i in 0 until teamsArray.length()){
//                val teamObj = teamsArray.getJSONObject(i)
//                val teamId = teamObj.getInt("idTeam")
//                val teamName = teamObj.getString("strTeam")
//                teamList.add(teamName)
//
//                urlStringLookups = "www.thesportsdb.com/api/v1/json/3/lookupequipment.php?id=$teamId"
//                val urlLookups = URL(urlStringLookups)
//                val conLookups: HttpURLConnection = urlLookups.openConnection() as HttpURLConnection
//
//                conLookups.requestMethod = "GET"
//                val jsonResponseLookups = conLookups.inputStream.bufferedReader().use { it.readText() }
//                val jsonLookups = JSONObject(jsonResponseLookups)
//                val lookupsArray = jsonLookups.getJSONArray("equipment")
//
//                for (j in 0 until lookupsArray.length()){
//                    val lookupObj = lookupsArray.getJSONObject(j)
//                    val strEquipment = lookupObj.getString("strEquipment")
//                    equipmentList.add(strEquipment)
//                }
//            }
//        }
//    }
////    return TeamEquipmentList(teamList, equipmentList)
//}
//
//@Preview
//@Composable
//private fun PreviewSearchAllClub() {
//    SearchAllClubs(
//        modifier = Modifier,
//    )
//}

package com.example.football_club


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import java.net.URL
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection


@Composable
fun SearchAllClubs(
    modifier: Modifier,
){
    var name by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var equipmentData by remember { mutableStateOf(mutableListOf<Pair<String, List<String>>>()) }

    Column (
        modifier.padding(10.dp)
    ){
        Row {
            TextField(
                value = name,
                onValueChange = { name = it.lowercase() },
                label = { Text("Enter the club :") },
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
                        equipmentData = readAPI2(name)
                    }
                }
            ) {
                // Inner content including an icon and a text label
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        DisplayEquipment(equipmentData)
    }

}

suspend fun readAPI2(name: String): MutableList<Pair<String, List<String>>> {
    val equipmentDataList = mutableListOf<Pair<String, List<String>>>()


    val urlStringSearch = "https://www.thesportsdb.com/api/v1/json/3/searchteams.php?t=$name"
    Log.d("webAPISearch",urlStringSearch)
    var urlStringLookups: String
    val urlSearch = URL(urlStringSearch)
    val conSearch: HttpURLConnection = urlSearch.openConnection() as HttpURLConnection

    withContext(Dispatchers.IO){
        conSearch.requestMethod = "GET"
        val jsonResponseTeam = conSearch.inputStream.bufferedReader().use { it.readText() }
        val jsonTeam = JSONObject(jsonResponseTeam)
        val teamsArray = jsonTeam.getJSONArray("teams")

        for (i in 0 until teamsArray.length()){
            val teamObj = teamsArray.getJSONObject(i)
            val teamId = teamObj.getString("idTeam")
            val teamName = teamObj.getString("strTeam")
            Log.d("teamID",teamId)
            Log.d("teamName",teamName)

            val equipmentList = mutableListOf<String>()

            urlStringLookups = "https://www.thesportsdb.com/api/v1/json/3/lookupequipment.php?id=133604"

            val urlLookups = URL(urlStringLookups)
            val conLookups: HttpURLConnection = urlLookups.openConnection() as HttpURLConnection

            conLookups.requestMethod = "GET"
            val jsonResponseLookups = conLookups.inputStream.bufferedReader().use { it.readText() }
            val jsonLookups = JSONObject(jsonResponseLookups)
            val lookupsArray = jsonLookups.getJSONArray("equipment")


            for (j in 0 until lookupsArray.length()){
                val lookupObj = lookupsArray.getJSONObject(j)
                val strEquipment = lookupObj.getString("strEquipment")
                equipmentList.add(strEquipment)
            }
            Log.d("EqiupList",equipmentList.toString())
            equipmentDataList.add(teamName to equipmentList)
        }

    }
    return equipmentDataList
}

@Composable
fun DisplayEquipment(equipmentData: List<Pair<String, List<String>>>) {

    Column {
        for ((teamName, equipmentList) in equipmentData) {
            Text("Team: $teamName")
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn {
                this.items(equipmentList) { equipment ->
                    AsyncImage(model = equipment, contentDescription = "Team equipment")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearchAllClubs(){
    SearchClubs(modifier = Modifier)
}
