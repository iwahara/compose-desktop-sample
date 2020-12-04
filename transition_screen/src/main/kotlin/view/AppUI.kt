package view

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun AppUI(){
    MaterialTheme {
        Content()
    }
}
@Composable
private fun Content(){
    var currentScreen by remember { mutableStateOf(AppStatus.currentScreen) }
    Column {
        Button(onClick = {
            currentScreen = when( currentScreen){
                is Screen.ContentList -> Screen.Content("123")
                is Screen.Content -> Screen.ContentList
            }
        }){
            Text("切り替え")
        }
        Crossfade(current = currentScreen) { screen ->
            when (screen) {
                is Screen.ContentList -> ContentListScreen()
                is Screen.Content -> ContentScreen(id = screen.id)
            }
        }
    }

}