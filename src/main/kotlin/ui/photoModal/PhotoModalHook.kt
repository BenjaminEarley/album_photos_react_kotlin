package ui.photoModal

import react.useState

typealias Toggle = () -> Unit

fun usePhotoModal(): Pair<Boolean, Toggle> {
    val (isShown, setIsShown) = useState(false)

    val toggle = { setIsShown(!isShown) }

    return Pair(isShown, toggle)
}