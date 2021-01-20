package ui.photos

import Photo
import dataRepository
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.div
import react.dom.img
import styled.css
import styled.styledDiv
import styled.styledH1
import styled.styledP
import ui.AlbumIdProps
import ui.AppStyles
import ui.common.modal.modal
import ui.common.modal.useModal

fun RBuilder.photos(id: Long): ReactElement {
    return child(photos) {
        attrs {
            albumId = id
        }
    }
}

val photos = functionalComponent<AlbumIdProps> { props ->
    val albumId = props.albumId
    val state = usePhotosViewModel(dataRepository, albumId)

    styledDiv {
        css {
            +AppStyles.Page
        }
        styledH1 {
            css {
                +AppStyles.Header
            }
            +state.albumTitle
        }
        photoGrid {
            photos = state.photos
        }
    }
}

external interface PhotoGridProps : RProps {
    var photos: List<Photo>
}

fun RBuilder.photoGrid(handler: PhotoGridProps.() -> Unit): ReactElement {
    return child(photoGrid) {
        attrs(handler)
    }
}

val photoGrid = functionalComponent<PhotoGridProps> { props ->
    styledDiv {
        css {
            +AppStyles.Grid
        }
        props.photos.forEach {
            photoCard {
                key = "${it.id}"
                title = it.title
                thumbnailUrl = it.thumbnailUrl
                url = it.url
            }
        }
    }
}

external interface PhotoProps : RProps {
    var title: String
    var thumbnailUrl: String
    var url: String
}

fun RBuilder.photoCard(handler: PhotoProps.() -> Unit): ReactElement {
    return child(photoCard) {
        attrs(handler)
    }
}

val photoCard = functionalComponent<PhotoProps> { props ->

    val (showModal, toggle) = useModal()

    div {
        div {
            attrs {
                onClickFunction = {
                    toggle()
                }
            }
            styledDiv {
                css {
                    +AppStyles.Card
                }
                img(src = props.thumbnailUrl, alt = props.title) {

                }
            }
            styledP {
                css {
                    +PhotoStyles.PhotoTitle
                }
                +props.title
            }
        }
//        modal {
//            isShown = showModal
//            hide = toggle
//            modalContent = photoModal {
//                title = props.title
//                url = props.url
//            }
//        }
    }
}