package ui.albums

import Album
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import react.useEffectWithCleanup
import react.useState
import repository.IDataRepository

data class AlbumsState(val albums: List<Album>)

fun useAlbumsViewModel(dataRepository: IDataRepository): AlbumsState {

    val (state, setState) = useState(AlbumsState(emptyList()))

    val scope = MainScope() + CoroutineName("AlbumsViewModel")

    useEffectWithCleanup(listOf(dataRepository)) {

        scope.launch {
            dataRepository.getAlbums().map { it.take(12) }.collect {
                setState(state.copy(albums = it))
            }
        };

        { scope.cancel() }
    }

    return state
}