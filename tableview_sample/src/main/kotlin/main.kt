import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.Window
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
fun main() = Window {

    DesktopMaterialTheme {
        Box(modifier = Modifier.fillMaxSize().padding(10.dp)) {
            val stateVertical = rememberLazyListState()
            val stateHorizontal = rememberScrollState()

            val rows = (0..100).toList()
            val cols = (0..10).toList()

            LazyColumnFor(
                items = rows,
                modifier = Modifier.fillMaxSize().padding(end = 12.dp, bottom = 12.dp),
                state = stateVertical
            ) { r ->

                ScrollableRow(scrollState = stateHorizontal) {
                    for (c in cols) {
                        TextBox("data_${r}-${c}")
                    }
                }
            }

            VerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                adapter = rememberScrollbarAdapter(
                    scrollState = stateVertical,
                    itemCount = rows.size,
                    averageItemSize = 20.dp
                )
            )
            HorizontalScrollbar(
                modifier = Modifier.align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                adapter = rememberScrollbarAdapter(stateHorizontal)
            )
        }

    }
}

@Composable
fun TextBox(text: String) {
    Box(
        modifier = Modifier.border(width = 1.dp, color = MaterialTheme.colors.onBackground)
            .preferredSize(80.dp, 20.dp)
    ) {
        Text(text)
    }
}