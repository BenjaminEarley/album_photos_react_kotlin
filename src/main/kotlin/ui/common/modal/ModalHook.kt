package ui.common.modal

import react.useState

typealias Toggle = () -> Unit

fun useModal(): Pair<Boolean, Toggle> {
    val (isShown, setIsShown) = useState<Boolean>(false)

    val toggle = { setIsShown(!isShown) }

    return Pair(isShown, toggle)
}