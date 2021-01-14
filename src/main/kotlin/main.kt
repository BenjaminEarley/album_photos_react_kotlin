import kotlinx.browser.document
import react.child
import react.dom.render
import ui.app

fun main() {
    render(document.getElementById("root")) {
        child(app)
    }
}