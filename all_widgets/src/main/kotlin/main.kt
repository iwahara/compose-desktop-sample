import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.Window
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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

fun main() = Window(title = "ウィンドウのタイトル", size = IntSize(600, 600)) {
    val fabCount = remember { mutableStateOf(0) }

    val scaffoldState = rememberScaffoldState()
    DesktopMaterialTheme {
        Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                            title = { Text("タイトル") },
                            navigationIcon = {
                                Icon(imageVector = Icons.Filled.Menu, modifier = Modifier.clickable(onClick =
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
                        Icon(imageVector = Icons.Filled.Add)
                    }
                },
                floatingActionButtonPosition = FabPosition.End,
                drawerContent = { Text(text = "drawerContent") },
        ) {
            Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
                LinearProgressBarSample()
                CheckBoxSample()
                Row {
                    Text("FAB Clicked ${fabCount.value}!")

                    CircularProgressIndicator(progress = fabCount.value * 0.01f)
                }
                RadioButtonSample()
                SliderSample()
                SnackBarSample()
                SwitchSample()
                TextFieldSample()
                Divider()
                CardSample()
                DropdownDemo()
            }
        }

    }

}

@Composable
fun CardSample() {
    Card {
        Text("カードだよ")
    }
}

@Composable
fun TextFieldSample() {
    val textField = remember { mutableStateOf("") }
    Row {
        TextField(value = textField.value, onValueChange = {
            textField.value = it
        })
        Text(textField.value)
    }
}

@Composable
fun SwitchSample() {
    val switchChecked = remember { mutableStateOf(false) }
    Row {
        Switch(checked = switchChecked.value, onCheckedChange = {
            switchChecked.value = it
        })
        Text(if (switchChecked.value) "チェック済み！" else "チェックなし！")
    }
}

@Composable
fun SnackBarSample() {
    val snackBarVisibleState = remember { mutableStateOf(false) }
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
}

@Composable
fun SliderSample() {
    val sliderVal = remember { mutableStateOf(0.5f) }
    Row {
        Slider(value = sliderVal.value, onValueChange = {
            sliderVal.value = it
        }, modifier = Modifier.weight(1f))
        Text("Slider ${sliderVal.value}", modifier = Modifier.weight(1f))
    }
}

@Composable
fun RadioButtonSample() {
    val selected = remember { mutableStateOf(true) }
    Row {
        RadioButton(selected = selected.value, onClick = {
            selected.value = !selected.value
        })
        Text(if (selected.value) "選択済み！" else "選択なし！")
    }
}

@Composable
fun CheckBoxSample() {
    val checked = remember { mutableStateOf(true) }
    Row {
        Checkbox(checked = checked.value,
                onCheckedChange = {
                    checked.value = it
                })
        Text(if (checked.value) "チェック済み！" else "チェックなし！")
    }
}

@Composable
fun LinearProgressBarSample() {
    val count = remember { mutableStateOf(0) }
    Row {
        Text("Clicked ${count.value}!")
        LinearProgressIndicator(progress = count.value * 0.01f, modifier = Modifier.align(Alignment.CenterVertically))
    }
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    count.value++
                }) {
            Text("ボタンを押すよ！")
        }
    }
}

@Composable
fun DropdownDemo() {
    val items = listOf("A", "B", "C", "D", "E", "F")
    val disabledValue = "B"
    val showMenu = remember { mutableStateOf(false) }
    val selectedIndex = remember { mutableStateOf(0) }

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

