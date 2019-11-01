package xyz.byxor.nfabot.features.thpsvideos.youtube

interface YoutubeApi {
    fun getAllVideosInPlaylist(playlistId: String): List<Video>
}
