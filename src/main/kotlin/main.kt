import kotlinx.browser.document
import network.DataService
import react.child
import react.dom.render
import repository.DataRepository
import ui.app

val dataRepository = DataRepository(DataService())

fun main() {
    render(document.getElementById("root")) {
        child(app)
    }
}