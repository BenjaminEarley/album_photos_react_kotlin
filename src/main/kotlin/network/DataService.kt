package network

import Album
import Photo
import kotlinx.browser.window
import kotlinx.coroutines.await

interface IDataService {
    suspend fun getAlbums(): List<Album>

    suspend fun getPhotos(albumId: Long): List<Photo>
}

class DataService : IDataService {

    override suspend fun getAlbums() =
        window.fetch("https://jsonplaceholder.typicode.com/albums")
            .await()
            .json()
            .await()
            .unsafeCast<List<Album>>()

    override suspend fun getPhotos(albumId: Long) =
        window.fetch("https://jsonplaceholder.typicode.com/albums/$albumId/photos")
            .await()
            .json()
            .await()
            .unsafeCast<List<Photo>>()
}