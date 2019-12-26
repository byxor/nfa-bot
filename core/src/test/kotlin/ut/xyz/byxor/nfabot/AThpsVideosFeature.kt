package ut.xyz.byxor.nfabot;

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test;
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import ut.xyz.byxor.nfabot.mocks.MockChatService
import xyz.byxor.nfabot.chat.ChatEventPublishers
import xyz.byxor.nfabot.chat.MessageEvent
import xyz.byxor.nfabot.features.thpsvideos.ThpsVideosFeature
import xyz.byxor.nfabot.features.thpsvideos.youtube.Video
import xyz.byxor.nfabot.features.thpsvideos.youtube.YoutubeApi
import xyz.byxor.nfabot.random.Random

@RunWith(Parameterized::class)
class AThpsVideosFeature(
    private val predeterminedVideoIndex: Int
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun parameters() = listOf(0, 1, 2, 3) // Run the test for each video index
    }

    @Before
    fun beforeEach() = configureMocks()

    @Test
    fun `Should post a random THPS Video when somebody says "!thpsvideo"`() {
        givenVideoWillBeChosen(predeterminedVideoIndex)
        whenSomebodySays("!thpsvideo")
        thenBotPostsVideo(predeterminedVideoIndex)
    }

    @Test
    fun `Should not respond to any other messages`() {
        whenSomebodySays("I enjoy watching THPS Videos")
        thenTheBotSaysNothing()
    }

    @Test
    fun `Should respond in the channel where the command was posted`() {
        givenVideoWillBeChosen(predeterminedVideoIndex)
        whenSomebodySays("!thpsvideo", "custom_channel")
        thenBotPostsVideo(predeterminedVideoIndex, "custom_channel")
    }

    private fun givenVideoWillBeChosen(index: Int) {
        given(random.index(any())).willReturn(index)
    }

    private fun whenSomebodySays(message: String, channel: String = "general") {
        val messageEvent = MessageEvent(message, "author", channel)
        chatEventPublishers.messages.publish(messageEvent)
    }

    private fun thenBotPostsVideo(predeterminedVideoIndex: Int, expectedChannel: String = "general") {
        val video = videos[predeterminedVideoIndex]
        assert(chatService.message.contains(video.getUrl()))
        println("${chatService.channel} $expectedChannel")
        assert(chatService.channel == expectedChannel)
    }

    private fun thenTheBotSaysNothing() {
        assert(chatService.message == "")
    }

    private fun configureMocks() {
        chatService = MockChatService()
        chatEventPublishers = ChatEventPublishers()
        random = mock()

        youtubeApi = mock()
        given(youtubeApi.getAllVideosInPlaylist(any())).willReturn(videos)

        thpsVideosFeature = ThpsVideosFeature(chatService, chatEventPublishers, random, youtubeApi)
        thpsVideosFeature.configure()
    }

    private lateinit var chatService: MockChatService
    private lateinit var chatEventPublishers: ChatEventPublishers
    private lateinit var random: Random
    private lateinit var youtubeApi: YoutubeApi
    private lateinit var thpsVideosFeature: ThpsVideosFeature

    private val videos = listOf(
            Video("aaa"),
            Video("bbb"),
            Video("ccc"),
            Video("ddd")
    )
}
