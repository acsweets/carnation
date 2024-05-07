import android.net.Uri

data class AudioFile(
    val id: Long,
    val title: String,
    val artist: String,
    val duration: Long,
    val uri: Uri
)