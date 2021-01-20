package ui.common.modal

import kotlinx.browser.document
import kotlinx.html.js.onClickFunction
import kotlinx.html.role
import kotlinx.html.tabIndex
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent
import react.*
import react.dom.createPortal
import styled.css
import styled.styledDiv

external interface ModalProps : RProps {
    var isShown: Boolean
    var hide: () -> Unit
    var modalContent: ReactElement
}

fun RBuilder.modal(handler: ModalProps.() -> Unit): ReactElement {
    return child(modal) {
        this.attrs(handler)
    }
}

val modal = functionalComponent<ModalProps> { props ->

    useEffectWithCleanup(listOf(props.isShown)) {
        val onKeyDown: (Event) -> Unit = { event ->
            if (event is KeyboardEvent && event.keyCode == 27 && props.isShown) {
                props.hide()
            }
        }
        document.addEventListener("keydown", onKeyDown, false);

        { document.removeEventListener("keydown", onKeyDown, false) }
    }

    val modal = styledDiv {
            css {
                +ModalStyles.Backdrop
            }
            attrs {
                onClickFunction = {
                    props.hide()
                }
            }
            styledDiv {
                css {
                    +ModalStyles.Wrapper
                }
                attrs {
                    tabIndex = "-1"
                    role = "dialog"
                }
                styledDiv {
                    css {
                        +ModalStyles.StyledModal
                    }
                    styledDiv {
                        css {
                            +ModalStyles.Content
                        }
                        +props.modalContent
                    }
                }
            }
        }

    if (props.isShown) createPortal(modal, document.body)
}
