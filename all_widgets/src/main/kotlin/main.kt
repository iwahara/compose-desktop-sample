import androidx.compose.desktop.Window
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
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
    val selected = remember { mutableStateOf(true) }
    val sliderVal = remember { mutableStateOf(0.5f) }
    val snackBarVisibleState = remember { mutableStateOf(false) }
    val switchChecked = remember { mutableStateOf(false) }
    val textField = remember { mutableStateOf("") }
    val scaffoldState = rememberScaffoldState()
    MaterialTheme {
        Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                            title = { BasicText("タイトル") },
                            navigationIcon = {
                                Icon(asset = Icons.Filled.Menu, modifier = Modifier.clickable(onClick =
                                {
                                    scaffoldState.drawerState.open() // or toggle
                                }))
                            },
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                            onClick = {
                                fabCount.value++
                            }) {
                        Icon(asset = Icons.Filled.Add)
                    }
                },
                floatingActionButtonPosition = FabPosition.End,
                drawerContent = { Text(text = "drawerContent") },
        ) {
            Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
                Row {
                    BasicText("Clicked ${count.value}!")
                    LinearProgressIndicator(progress = count.value * 0.01f, modifier = Modifier.align(Alignment.CenterVertically))
                }

                Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            count.value++
                        }) {
                    BasicText("ボタンを押すよ！")
                }
                Row {
                    Checkbox(checked = checked.value,
                            onCheckedChange = {
                                checked.value = it
                            })
                    BasicText(if (checked.value) "チェック済み！" else "チェックなし！")
                }
                Row {
                    BasicText("FAB Clicked ${fabCount.value}!")

                    CircularProgressIndicator(progress = fabCount.value * 0.01f)
                }
                Row {
                    RadioButton(selected = selected.value, onClick = {
                        selected.value = !selected.value
                    })
                    BasicText(if (selected.value) "選択済み！" else "選択なし！")
                }
                Row {
                    Slider(value = sliderVal.value, onValueChange = {
                        sliderVal.value = it
                    }, modifier = Modifier.weight(1f))
                    BasicText("Slider ${sliderVal.value}", modifier = Modifier.weight(1f))
                }
                Button(onClick = { snackBarVisibleState.value = !snackBarVisibleState.value }) {
                    if (snackBarVisibleState.value) {
                        Text("Snackbarを隠す")
                    } else {
                        Text("Snackbarを表示")
                    }
                }
                if (snackBarVisibleState.value) {
                    Snackbar(
                            text = { Text(text = "スナックバーやでー!") },
                            action = {
                                Button(onClick = {}) {
                                    Text("ボタンだよ")
                                }
                            },
                    )
                }
                Row {
                    Switch(checked = switchChecked.value, onCheckedChange = {
                        switchChecked.value = it
                    })
                    BasicText(if (switchChecked.value) "チェック済み！" else "チェックなし！")
                }
                Row {
                    TextField(value = textField.value, onValueChange = {
                        textField.value = it
                    })
                    BasicText(textField.value)
                }
                Divider()
                Card {
                    BasicText("カードだよ")
                }
                //NoSuchMethodErrorが出るのでコメントアウト
                //DropdownDemo()
            }
        }

    }

}

@Composable
fun DropdownDemo() {
    val items = listOf("A", "B", "C", "D", "E", "F")
    val disabledValue = "B"
    var showMenu = remember { mutableStateOf(false) }
    var selectedIndex = remember { mutableStateOf(0) }

    DropdownMenu(
            toggle = {
                Text(items[selectedIndex.value], modifier = Modifier.fillMaxWidth().clickable(onClick = { showMenu.value = true }))
            },
            expanded = showMenu.value,
            onDismissRequest = { showMenu.value = false },
    ) {
        items.forEachIndexed { index, s ->
            DropdownMenuItem(
                    onClick = {
                        selectedIndex.value = index
                        showMenu.value = false
                    }
            ) {
                Text(text = s)
            }
        }
    }
}

