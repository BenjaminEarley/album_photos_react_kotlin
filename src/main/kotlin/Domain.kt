data class Album(
    val id: Long,
    val userId: Long,
    val title: String
)

data class Photo(
    val id: Long,
    val albumId: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
