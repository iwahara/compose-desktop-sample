
sealed class Screen {
    object ContentList : Screen()
    data class Content(val id: String) : Screen()
}

object AppStatus {
    var currentScreen: Screen = Screen.ContentList
}

fun navigateTo(destination: Screen) {
    AppStatus.currentScreen = destination
}