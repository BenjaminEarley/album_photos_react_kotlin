package ui

import kotlinx.css.*
import kotlinx.css.Position.absolute
import kotlinx.css.Position.relative
import kotlinx.css.properties.boxShadow
import styled.StyleSheet

object AppStyles : StyleSheet("AppStyles", isStatic = true) {

    val Page by css {
        padding(62.px)
    }

    val TopBar by css {
        height = 62.px
        width = 100.pct
        position = relative
        float = Float.left
        boxShadow(Color.black.withAlpha(0.05), 0.px, 2.px, 0.px, 2.px)
    }

    val Logo by css {
        position = absolute
        paddingLeft = 30.px
        top = 50.pct - 24.px / 2
    }
}