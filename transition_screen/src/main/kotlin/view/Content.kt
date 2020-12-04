package view

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Composable
fun ContentScreen(id : String){

    var text by androidx.compose.runtime.remember { mutableStateOf(id) }
    Button(onClick = {
        text = "content!"
    }) {
        Text(text)
    }
}