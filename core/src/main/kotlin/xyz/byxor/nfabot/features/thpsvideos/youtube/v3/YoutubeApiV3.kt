package xyz.byxor.nfabot.features.thpsvideos.youtube.v3

import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.youtube.YouTube
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

    override fun getAllVideosInPlaylist(playlistId: String): List<Video> {
        return getRemainingVideosInPlaylist(playlistId, "")
    }

    private fun getRemainingVideosInPlaylist(playlistId: String, pageToken: String): List<Video> {
        val request = when(pageToken) {
            "" -> newPlaylistRequest(playlistId)
            else -> newPlaylistRequest(playlistId).setPageToken(pageToken)
        }

        val response = request.execute()

        val videosOnPage = response.items.map { item ->
            Video(item.contentDetails.videoId)
        }

        val morePagesExist = response.nextPageToken != null
        return if (morePagesExist) {
            concatenate(videosOnPage, getRemainingVideosInPlaylist(playlistId, response.nextPageToken))
        } else {
            videosOnPage
        }
    }

    private fun concatenate(a: List<Video>, b: List<Video>) = listOf(
            *a.toTypedArray(),
            *b.toTypedArray()
    )

    private fun newPlaylistRequest(playlistId: String) = service
            .playlistItems()
            .list("contentDetails")
            .setMaxResults(50L)
            .setPlaylistId(playlistId)
}
