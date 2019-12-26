package xyz.byxor.nfabot.features.thpsvideos

import xyz.byxor.nfabot.chat.ChatEventPublishers
import xyz.byxor.nfabot.chat.ChatService
import xyz.byxor.nfabot.chat.MessageEvent
import xyz.byxor.nfabot.events.EventSubscriber
import xyz.byxor.nfabot.features.Feature
import xyz.byxor.nfabot.features.thpsvideos.youtube.Video
import xyz.byxor.nfabot.features.thpsvideos.youtube.YoutubeApi
import xyz.byxor.nfabot.features.thpsvideos.youtube.v3.YoutubeApiV3
import xyz.byxor.nfabot.random.Random

class ThpsVideosFeature(
        private val chatService: ChatService,
        private val chatEventPublishers: ChatEventPublishers,
        private val random: Random,
        private val youtubeApi: YoutubeApi
): Feature, EventSubscriber<MessageEvent> {

    private val thpsVideosUploads = "UUIDuzEnSK30aJn1bSVNaaKg"

    private var videos: List<Video>? = null

    override fun configure() {
        chatEventPublishers.messages.subscribe(this)
    }

    override fun onEvent(event: MessageEvent) {
        if (event.message == "!thpsvideo") {
            val videos = getVideos()
            val video = videos[random.index(videos.size)]
            chatService.sendMessage(video.getUrl(), event.channel)
        }
    }

    private fun getVideos(): List<Video> {
        if (videos == null) {
            videos = youtubeApi.getAllVideosInPlaylist(thpsVideosUploads)
        }
        return videos!!
    }
}