package ui.albums

import kotlinx.css.*
import kotlinx.css.properties.lh
import styled.StyleSheet

object AlbumStyles : StyleSheet("AlbumStyles", isStatic = true) {

    val AlbumTitle by css {
        fontStyle = FontStyle.normal
        fontWeight = FontWeight.w500
        fontSize = 14.px
        lineHeight = 17.px.lh
        letterSpacing = 0.1.em
        textTransform = TextTransform.uppercase
        padding(8.px)
        display = Display.inlineBlock
        verticalAlign = VerticalAlign.middle
        color = Color("#32343C")
    }
}