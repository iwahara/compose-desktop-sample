package view

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import navigateTo

@Composable
fun ContentListScreen(){
    Button(onClick = {
        navigateTo(Screen.Content("id"))
    }) {
        Text("transit content")
    }
}