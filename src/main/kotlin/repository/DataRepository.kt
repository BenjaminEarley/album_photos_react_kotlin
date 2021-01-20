package repository

import Album
import Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import network.IDataService

interface IDataRepository {
    suspend fun getAlbums(): Flow<List<Album>>

    suspend fun getPhotos(albumId: Long): Flow<List<Photo>>
}

data class State(val albums: List<Album>, val photosByAlbum: Map<Long, List<Photo>>)

class DataRepository(private val dataService: IDataService) : IDataRepository {

    private val state = MutableStateFlow(State(albums = emptyList(), photosByAlbum = emptyMap()))

    override suspend fun getAlbums(): Flow<List<Album>> {
        if (state.value.albums.isEmpty()) reloadAlbums()
        return state.map { it.albums }
    }

    override suspend fun getPhotos(albumId: Long): Flow<List<Photo>> {
        if (!state.value.photosByAlbum.containsKey(albumId)) reloadPhotos(albumId)
        return state.map { it.photosByAlbum.getOrElse(albumId) { emptyList() } }
    }

    private suspend fun reloadAlbums() {
            val albums = dataService.getAlbums()
            state.value = state.value.copy(albums = albums)
    }

    private suspend fun reloadPhotos(albumId: Long) {
            val photos = dataService.getPhotos(albumId)
            state.value = state.value.copy(photosByAlbum = state.value.photosByAlbum + (albumId to photos))
    }

}