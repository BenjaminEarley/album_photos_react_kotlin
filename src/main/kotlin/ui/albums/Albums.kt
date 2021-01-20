package ui.albums

import Album
import dataRepository
import kotlinx.html.js.onClickFunction
import react.*
import react.router.dom.useHistory
import styled.css
import styled.styledDiv
import styled.styledH1
import ui.AppStyles

fun RBuilder.albums(): ReactElement {
    return child(albums)
}

val albums = functionalComponent<RProps> {

    val history = useHistory()
    val state = useAlbumsViewModel(dataRepository)

    styledDiv {
        css {
            +AppStyles.Page
        }
        styledH1 {
            css {
                +AppStyles.Header
            }
            +"Photo Albums"
        }
        albumGrid {
            albums = state.albums
            onClick = { album -> history.push("/album/${album.id}") }
        }
    }
}

external interface AlbumGridProps : RProps {
    var albums: List<Album>
    var onClick: (album: Album) -> Unit
}

fun RBuilder.albumGrid(handler: AlbumGridProps.() -> Unit): ReactElement {
    return child(albumGrid) {
        attrs(handler)
    }
}

val albumGrid = functionalComponent<AlbumGridProps> { props ->
    styledDiv {
        css {
            +AppStyles.Grid
        }
        props.albums.forEach {
            albumCard {
                key = "${it.id}"
                title = it.title
                onClick = { props.onClick(it) }
            }
        }
    }
}

external interface AlbumProps : RProps {
    var title: String
    var onClick: () -> Unit
}

fun RBuilder.albumCard(handler: AlbumProps.() -> Unit): ReactElement {
    return child(albumCard) {
        attrs(handler)
    }
}

val albumCard = functionalComponent<AlbumProps> { props ->
    styledDiv {
        css {
            +AppStyles.Card
        }
        attrs {
            onClickFunction = {
                props.onClick()
            }
        }
        styledDiv {
            css {
                +AlbumStyles.AlbumTitle
            }
            +props.title
        }
    }
}