package xyz.byxor.nfabot.features.thpsvideos

import xyz.byxor.nfabot.chat.ChatEventPublishers
import xyz.byxor.nfabot.chat.ChatService
import xyz.byxor.nfabot.chat.MessageEvent
import xyz.byxor.nfabot.events.EventSubscriber
import xyz.byxor.nfabot.features.Feature
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

    override fun configure() {
        chatEventPublishers.messages.subscribe(this)
    }

    override fun onEvent(event: MessageEvent) {
        if (event.message == "!thpsvideo") {
            val videos = youtubeApi.getAllVideosInPlaylist(thpsVideosUploads)
            val video = videos[random.index(videos.size)]
            chatService.sendMessage(video.getUrl())
        }
    }
}