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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
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
                TextSample()
                CardSample()
                TextFieldSample()
                CheckBoxSample()
                RadioButtonSample()
                SwitchSample()
                SliderSample()
                ProgressSample()
                DropdownDemo()

                SnackBarSample()

            }
        }

    }

}

@Composable
fun ProgressSample() {
    val windowState = remember { mutableStateOf(false) }
    val progress = remember { mutableStateOf(0f) }

    Button(onClick = {
        windowState.value = true
    }) {
        Text("Progressのサンプル")
    }
    if (windowState.value) {
        Window(title = "Progressのサンプル", size = IntSize(600, 600), location = IntOffset(100, 100), centered = false,
                onDismissRequest = {
                    windowState.value = false
                }) {
            Column {
                Text("progress ${progress.value}")
                Slider(value = progress.value, onValueChange = {
                    progress.value = it
                })
                LinearProgressIndicator(progress = progress.value)
                CircularProgressIndicator(progress = progress.value)
            }
        }

    }
}

@Composable
fun TextSample() {
    val windowState = remember { mutableStateOf(false) }

    Button(onClick = {
        windowState.value = true
    }) {
        Text("Textのサンプル")
    }
    if (windowState.value) {
        Window(title = "Textのサンプル", size = IntSize(600, 600), location = IntOffset(100, 100), centered = false,
                onDismissRequest = {
                    windowState.value = false
                }) {
            Column {
                Text("テキスト")
                Divider()
                Text(AnnotatedString(
                        text = "複数のスタイルを割り当てる",
                        // make "Hello" italic.
                        spanStyles = listOf(
                                AnnotatedString.Range(SpanStyle(fontStyle = FontStyle.Italic), 0, 5)
                        ),
                        // create two paragraphs with different alignment and indent settings.
                        paragraphStyles = listOf(
                                AnnotatedString.Range(ParagraphStyle(textAlign = TextAlign.Center), 0, 6),
                                //AnnotatedString.Range(ParagraphStyle(textIndent = TextIndent(5.sp)), 6, 11)
                        )
                ))
            }
        }
    }
}

@Composable
fun CardSample() {
    val windowState = remember { mutableStateOf(false) }

    Button(onClick = {
        windowState.value = true
    }) {
        Text("Cardのサンプル")
    }
    if (windowState.value) {
        Window(title = "Cardのサンプル", size = IntSize(600, 600), location = IntOffset(100, 100), centered = false,
                onDismissRequest = {
                    windowState.value = false
                }) {
            Column {
                Card(Modifier.fillMaxWidth().padding(8.dp), elevation = 8.dp) {
                    Text("カードだよ")
                }
            }
        }

    }
}

@Composable
fun TextFieldSample() {
    val windowState = remember { mutableStateOf(false) }
    val textField = remember { mutableStateOf("") }
    Button(onClick = {
        windowState.value = true
    }) {
        Text("TextFieldのサンプル")
    }
    if (windowState.value) {
        Window(title = "TextFieldのサンプル", size = IntSize(600, 600), location = IntOffset(100, 100),
                centered = false,
                onDismissRequest = {
                    windowState.value = false
                }) {

            Column {
                TextField(value = textField.value, onValueChange = {
                    textField.value = it
                }, label = {
                    Text("ラベル")
                }, placeholder = {
                    Text("プレースホルダー")
                })
                Text(textField.value)
            }
        }
    }

}

@Composable
fun SwitchSample() {
    val windowState = remember { mutableStateOf(false) }
    val switchChecked = remember { mutableStateOf(false) }
    Button(onClick = {
        windowState.value = true
    }) {
        Text("Switchのサンプル")
    }
    if (windowState.value) {
        Window(title = "Switchのサンプル", size = IntSize(600, 600), location = IntOffset(100, 100), centered = false,
                onDismissRequest = {
                    windowState.value = false
                }) {
            Column {
                Switch(checked = switchChecked.value, onCheckedChange = {
                    switchChecked.value = it
                })
                Text(if (switchChecked.value) "チェック済み！" else "チェックなし！")
            }
        }

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
    val windowState = remember { mutableStateOf(false) }
    val sliderVal = remember { mutableStateOf(0.5f) }
    Button(onClick = {
        windowState.value = true
    }) {
        Text("Sliderのサンプル")
    }
    if (windowState.value) {
        Window(title = "Sliderのサンプル", size = IntSize(600, 600), location = IntOffset(100, 100), centered = false,
                onDismissRequest = {
                    windowState.value = false
                }) {
            Column {
                Slider(value = sliderVal.value, onValueChange = {
                    sliderVal.value = it
                }, modifier = Modifier.weight(1f))
                Text("Slider ${sliderVal.value}", modifier = Modifier.weight(1f))
            }
        }

    }
}

@Composable
fun RadioButtonSample() {
    val windowState = remember { mutableStateOf(false) }
    val selected = remember { mutableStateOf(true) }
    Button(onClick = {
        windowState.value = true
    }) {
        Text("RadioButtonのサンプル")
    }
    if (windowState.value) {
        Window(title = "RadioButtonのサンプル", size = IntSize(600, 600), location = IntOffset(100, 100), centered = false,
                onDismissRequest = {
                    windowState.value = false
                }) {
            Column {

                RadioButton(selected = selected.value, onClick = {
                    selected.value = !selected.value
                })
                Text(if (selected.value) "選択済み！" else "選択なし！")
            }
        }

    }
}

@Composable
fun CheckBoxSample() {
    val windowState = remember { mutableStateOf(false) }
    val checked = remember { mutableStateOf(true) }
    Button(onClick = {
        windowState.value = true
    }) {
        Text("CheckBoxのサンプル")
    }
    if (windowState.value) {
        Window(title = "CheckBoxのサンプル", size = IntSize(600, 600), location = IntOffset(100, 100), centered = false,
                onDismissRequest = {
                    windowState.value = false
                }) {
            Column {
                Checkbox(checked = checked.value,
                        onCheckedChange = {
                            checked.value = it
                        })
                Text(if (checked.value) "チェック済み！" else "チェックなし！")
            }
        }

    }
}


@Composable
fun DropdownDemo() {

    val windowState = remember { mutableStateOf(false) }

    val items = listOf("A", "B", "C", "D", "E", "F")
    val showMenu = remember { mutableStateOf(false) }
    val selectedIndex = remember { mutableStateOf(0) }
    Button(onClick = {
        windowState.value = true
    }) {
        Text("DropdownMenuのサンプル")
    }
    if (windowState.value) {
        Window(title = "DropdownMenuのサンプル", size = IntSize(600, 600), location = IntOffset(100, 100), centered = false,
                onDismissRequest = {
                    windowState.value = false
                }) {
            Column {
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
        }

    }
}

