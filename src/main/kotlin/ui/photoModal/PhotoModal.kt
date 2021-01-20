package ui.photoModal

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
import styled.styledImg
import styled.styledP

external interface ModalProps : RProps {
    var isShown: Boolean
    var hide: () -> Unit
    var title: String
    var url: String
}

fun RBuilder.photoModal(handler: ModalProps.() -> Unit): ReactElement {
    return child(photoModal) {
        this.attrs(handler)
    }
}

val photoModal = functionalComponent<ModalProps> { props ->

    useEffectWithCleanup(listOf(props.isShown)) {
        val onKeyDown: (Event) -> Unit = { event ->
            if (event is KeyboardEvent && event.keyCode == 27 && props.isShown) {
                props.hide()
            }
        }
        document.body?.style?.overflowY = if (props.isShown) "hidden" else "unset"

        document.addEventListener("keydown", onKeyDown, false);
        { document.removeEventListener("keydown", onKeyDown, false) }
    }

    fun modal(): ReactElement = styledDiv {
        css {
            +PhotoModalStyles.Backdrop
        }
        attrs {
            onClickFunction = {
                props.hide()
            }
        }
        styledDiv {
            css {
                +PhotoModalStyles.Wrapper
            }
            attrs {
                tabIndex = "-1"
                role = "dialog"
            }
            styledDiv {
                css {
                    +PhotoModalStyles.StyledModal
                }
                styledDiv {
                    css {
                        +PhotoModalStyles.Content
                    }
                    photoModalView {
                        title = props.title
                        url = props.url
                    }
                }
            }
        }
    }

    if (props.isShown) createPortal(modal(), document.body)
}

external interface PhotoModalProps : RProps {
    var title: String
    var url: String
}

fun RBuilder.photoModalView(handler: PhotoModalProps.() -> Unit): ReactElement {
    return child(photoModalView) {
        this.attrs(handler)
    }
}

val photoModalView = functionalComponent<PhotoModalProps> { props ->
    styledDiv {
        css {
            +PhotoModalStyles.Modal
        }
        styledImg(src = props.url, alt = props.title) {
            css {
                +PhotoModalStyles.ModalImage
            }
        }
        styledP {
            css {
                +PhotoModalStyles.PhotoTitle
            }
            +props.title
        }
    }
}
