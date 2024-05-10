package com.example.football_club

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Create a common button
@Composable
fun AppButton(label: String, onClick: () -> Unit, enabled: Boolean, modifier: Modifier) {
    Button(
        onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        enabled = enabled
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight(600))
        )
    }
}

@Preview(name = "HomeButton")
@Composable
private fun PreviewHomeButton() {
    AppButton(onClick = {}, label = "Football Clubs", enabled = true, modifier = Modifier)
}