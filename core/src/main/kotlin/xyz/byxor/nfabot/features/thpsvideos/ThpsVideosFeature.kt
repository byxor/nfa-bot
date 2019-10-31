import xyz.byxor.nfabot.features.thpsvideos.youtube.v3.YoutubeApiV3

fun main() {
//    val urls = (ClassLoader.getSystemClassLoader() as URLClassLoader).urLs
//    for (url in urls) {
//        System.out.println(url)
//    }

    val thpsVideosUploadsPlaylistId = "UUIDuzEnSK30aJn1bSVNaaKg"

    val api = YoutubeApiV3()
    api.getAllVideosInPlaylist(thpsVideosUploadsPlaylistId)
}