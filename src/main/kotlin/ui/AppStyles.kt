package ui

import kotlinx.css.*
import kotlinx.css.Position.absolute
import kotlinx.css.Position.relative
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.lh
import styled.StyleSheet

object AppStyles : StyleSheet("AppStyles", isStatic = true) {

    val Page by css {
        padding(62.px)
    }

    val Header by css {
        fontStyle = FontStyle.normal
        fontWeight = FontWeight.w500
        fontSize = 44.px
        lineHeight = 54.px.lh
        textAlign = TextAlign.center
        paddingTop = 94.px
        paddingBottom = 94.px
        color = Color("#32343C")
    }

    val TopBar by css {
        height = 62.px
        width = 100.pct
        position = relative
        float = Float.left
        boxShadow(Color.black.withAlpha(0.05), 0.px, 2.px, 0.px, 0.px)
    }

    val Logo by css {
        position = absolute
        paddingLeft = 30.px
        top = 50.pct - 24.px / 2
    }

    val Grid by css {
        display = Display.grid
        gridTemplateColumns = GridTemplateColumns("repeat(4, 150px)")
        rowGap = Gap("${80.px}")
        columnGap = Gap("${60.px}")
        width = 100.pct
        justifyContent = JustifyContent.center
    }

    val Card by css {
        background = "#F0F2F5"
        height = 150.px
        width = 150.px
        lineHeight = LineHeight("${150.px}")
        textAlign = TextAlign.center


    }
}