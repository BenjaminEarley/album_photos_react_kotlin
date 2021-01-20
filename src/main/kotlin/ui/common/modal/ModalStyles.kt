package ui.common.modal

import kotlinx.css.*
import kotlinx.css.properties.Transforms
import kotlinx.css.properties.translate
import styled.StyleSheet

object ModalStyles : StyleSheet("ModalStyles", isStatic = true) {

    val Wrapper by css {
        position = Position.fixed
        top = 50.pct
        left = 50.pct
        transform = Transforms().apply { translate((-50).pct, (-50).pct) }
        zIndex = 700
        width = LinearDimension.inherit
        outline = Outline.none
    }

    val Backdrop by css {
        position = Position.fixed
        width = 100.pct
        height = 100.pct
        top = LinearDimension("0")
        left = LinearDimension("0")
        background = "rgba(0, 0, 0, 0.4)"
        zIndex = 500
    }

    val StyledModal by css {
        zIndex = 100
        background = "#FFFFFF"
        position = Position.relative
        margin = "auto"
    }

    val Content by css {
        overflowX = Overflow.hidden
        overflowY = Overflow.auto
    }
}