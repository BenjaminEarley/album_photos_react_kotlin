package repository

import Album
import Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import network.IDataService

interface IDataRepository {
    fun getAlbums(scope: CoroutineScope): Flow<List<Album>>

    fun getPhotos(scope: CoroutineScope, albumId: Long): Flow<List<Photo>>
}

data class State(val albums: List<Album>, val photosByAlbum: Map<Long, List<Photo>>)

class DataRepository(private val dataService: IDataService) : IDataRepository {

    private val state = MutableStateFlow(State(albums = emptyList(), photosByAlbum = emptyMap()))

    override fun getAlbums(scope: CoroutineScope): Flow<List<Album>> {
        if (state.value.albums.isEmpty()) scope.reloadAlbums()
        return state.map { it.albums }
    }

    override fun getPhotos(scope: CoroutineScope, albumId: Long): Flow<List<Photo>> {
        if (!state.value.photosByAlbum.containsKey(albumId)) scope.reloadPhotos(albumId)
        return state.map { it.photosByAlbum.getOrElse(albumId) { emptyList() } }
    }

    private fun CoroutineScope.reloadAlbums() {
        launch {
            val albums = dataService.getAlbums()
            state.value = state.value.copy(albums = albums)
        }
    }

    private fun CoroutineScope.reloadPhotos(albumId: Long) {
        launch {
            val photos = dataService.getPhotos(albumId)
            state.value = state.value.copy(photosByAlbum = state.value.photosByAlbum + (albumId to photos))
        }
    }

}