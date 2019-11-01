package xyz.byxor.nfabot.features.thpsvideos.youtube.v3

import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.PlaylistItemListResponse
import xyz.byxor.nfabot.features.thpsvideos.youtube.Video
import xyz.byxor.nfabot.features.thpsvideos.youtube.YoutubeApi
import java.io.FileInputStream
import java.io.InputStreamReader

class YoutubeApiV3 : YoutubeApi {

    private val applicationName = "NFA Bot"
    private val secretPath = "/home/brandon/Documents/youtube_client_secret.json"

    private lateinit var service: YouTube

    init {
        configureService()
    }

    private fun configureService() {
        val jsonFactory = JacksonFactory.getDefaultInstance()
        val scopes = listOf("https://www.googleapis.com/auth/youtube.readonly")

        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()

        val secrets = GoogleClientSecrets.load(jsonFactory, InputStreamReader(FileInputStream(secretPath)))
        val flow = GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, secrets, scopes).build()

        val credential = AuthorizationCodeInstalledApp(flow, LocalServerReceiver()).authorize("user")

        service = YouTube.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName(applicationName)
                .build()
    }

    override fun getAllVideosInPlaylist(playlistId: String) = getRemainingVideosInPlaylist(playlistId)

    private fun getRemainingVideosInPlaylist(playlistId: String, pageToken: String = ""): List<Video> {
        val request = createPlaylistRequest(playlistId, pageToken)

        val (videosOnThisPage, morePagesExist, nextPageToken) = request.extractVideosAndNextPageInformation()

        return if (morePagesExist) {
            val videosOnLaterPages = getRemainingVideosInPlaylist(playlistId, nextPageToken)
            concatenate(videosOnThisPage, videosOnLaterPages)
        } else {
            videosOnThisPage
        }
    }

    private fun createPlaylistRequest(playlistId: String, pageToken: String) = service
                .playlistItems()
                .list("contentDetails")
                .setMaxResults(50L)
                .setPlaylistId(playlistId)
                .setPageToken(pageToken)

    private fun PlaylistRequest.extractVideosAndNextPageInformation(): Triple<List<Video>, Boolean, String> {
        val response = this.execute()
        return Triple(response.getVideos(), response.morePagesExist(), response.nextPageToken)
    }

    private fun PlaylistItemListResponse.getVideos() = items.map { playlistItem ->
        Video(playlistItem.contentDetails.videoId)
    }

    private fun PlaylistItemListResponse.morePagesExist() = nextPageToken != null

    private fun concatenate(a: List<Video>, b: List<Video>) = listOf(
            *a.toTypedArray(),
            *b.toTypedArray()
    )
}

private typealias PlaylistRequest = YouTube.PlaylistItems.List
private typealias PlaylistResponse = PlaylistItemListResponse