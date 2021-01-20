package ui.photos

import react.*
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledP


external interface PhotoModalProps : RProps {
    var title: String
    var url: String
}

fun RBuilder.photoModal(handler: PhotoModalProps.() -> Unit): ReactElement {
    return child(photoModal) {
        this.attrs(handler)
    }
}

val photoModal = functionalComponent<PhotoModalProps> { props ->
    styledDiv {
        css {
            +PhotoStyles.Modal
        }
        styledImg(src = props.url, alt = props.title) {
            css {
                +PhotoStyles.ModalImage
            }
        }
        styledP {
            css {
                +PhotoStyles.PhotoTitle
            }
            +props.title
        }
    }
}
