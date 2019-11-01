import xyz.byxor.nfabot.features.thpsvideos.youtube.v3.YoutubeApiV3

fun main() {
    val thpsVideosUploadsPlaylistId = "UUIDuzEnSK30aJn1bSVNaaKg"

    val api = YoutubeApiV3()
    val videos = api.getAllVideosInPlaylist(thpsVideosUploadsPlaylistId)

    videos.forEach { video ->
        println(video.getUrl())
    }

    println("This channel has ${videos.size} videos!")
}