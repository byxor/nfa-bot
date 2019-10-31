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

    private val APPLICATION_NAME = "NFA Bot"
    private val SECRET_PATH = "/home/brandon/Documents/youtube_client_secret.json"

    private lateinit var service: YouTube

    init {
        configureService()
    }

    private fun configureService() {
        val jsonFactory = JacksonFactory.getDefaultInstance()
        val scopes = listOf("https://www.googleapis.com/auth/youtube.readonly")

        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()



        val secrets = GoogleClientSecrets.load(jsonFactory, InputStreamReader(FileInputStream(SECRET_PATH)))
        val flow = GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, secrets, scopes).build()

        val credential = AuthorizationCodeInstalledApp(flow, LocalServerReceiver()).authorize("user")

        service = YouTube.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName(APPLICATION_NAME)
                .build()
    }

    override fun getAllVideosInPlaylist(playlistId: String): List<Video> {
        val request = service.playlistItems().list("contentDetails")
        val response = request.setMaxResults(50)
                .setPlaylistId(playlistId)
                .execute()
        println(response)
        return listOf()
    }

}
