package ui.photos

import kotlinx.css.*
import kotlinx.css.properties.lh
import styled.StyleSheet

object PhotoStyles : StyleSheet("PhotoStyles", isStatic = true) {

    val PhotoTitle by css {
        fontStyle = FontStyle.normal
        fontWeight = FontWeight.w500
        fontSize = 16.px
        lineHeight = 20.px.lh
        textAlign = TextAlign.center
        color = Color("#838690")
    }
}