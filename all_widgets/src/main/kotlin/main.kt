import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

fun main() = Window(title = "Compose for Desktop", size = IntSize(600, 600)) {
    val count = remember { mutableStateOf(0) }
    val checked = remember { mutableStateOf(true) }
    val fabCount = remember { mutableStateOf(0) }
    MaterialTheme {
        Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                            onClick = {
                                fabCount.value++
                            }) {
                        Icon(asset = Icons.Filled.Add)
                    }
                },
        ) {
            Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
                BasicText("Clicked ${count.value}!")
                Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            count.value++
                        }) {
                    BasicText("ボタンを押すよ！")
                }
                Checkbox(checked = checked.value, modifier = Modifier.align(Alignment.CenterHorizontally),
                        onCheckedChange = {
                            checked.value = it
                        })
                BasicText(if (checked.value) "チェック済み！" else "チェックなし！")
                BasicText("Clicked ${fabCount.value}!")
            }
        }


    }
}
