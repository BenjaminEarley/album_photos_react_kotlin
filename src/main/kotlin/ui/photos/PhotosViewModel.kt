package ui.photos

import Photo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import react.useEffectWithCleanup
import react.useState
import repository.IDataRepository

data class PhotosState(val albumTitle: String, val photos: List<Photo>)

fun usePhotosViewModel(dataRepository: IDataRepository, albumId: Long): PhotosState {

    val (state, setState) = useState(PhotosState("", emptyList()))

    val scope = MainScope() + CoroutineName("PhotosViewModel")

    useEffectWithCleanup(listOf(dataRepository, albumId)) {

        scope.launch {
            combine(
                dataRepository.getAlbums().map { albums -> albums.find { "${it.id}" == "$albumId" }?.title ?: "" },
                dataRepository.getPhotos(albumId).map { it.take(15) }
            ) { title, photos ->
                setState(state.copy(albumTitle = title, photos = photos))
            }
                .collect()
        };

        { scope.cancel() }
    }

    return state
}