package ui

import react.RProps
import react.child
import react.dom.div
import react.functionalComponent
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.routeLink
import react.router.dom.switch
import styled.css
import styled.styledDiv
import styled.styledImg
import ui.albums.albums

val app = functionalComponent<RProps> {
    browserRouter {
        child(appBar)
        child(pageSwitch)
    }
}

external interface AlbumIdProps : RProps {
    var albumId: Long
}

val pageSwitch = functionalComponent<RProps> {
    switch {
        route("/", exact = true) {
            albums()
        }
        route<AlbumIdProps>("/album/:albumId") { props ->
            div {

            }
        }
    }
}

val appBar = functionalComponent<RProps> {
    styledDiv {
        css {
            +AppStyles.TopBar
        }
        routeLink("/") {
            styledImg(src = "logo.png", alt = "logo") {
                css {
                    +AppStyles.Logo
                }
            }
        }
    }
}