package ut.xyz.byxor.nfabot

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import ut.xyz.byxor.nfabot.mocks.MockChatService
import xyz.byxor.nfabot.chat.ChatEventPublishers
import xyz.byxor.nfabot.chat.ChatService
import xyz.byxor.nfabot.chat.MessageEvent
import xyz.byxor.nfabot.features.TetrisFeature

class ATetrisFeature {

    @Before
    fun beforeEach() = configureMocks()

    @Test
    fun `Should say "BOOM tetris for Simon!" when Simon says "!tetris"`() {
        whenSomebodySays("Simon", "!tetris")
        thenTheBotSays("BOOM tetris for Simon!")
    }

    @Test
    fun `Should say "BOOM tetris for Jeff!" when Jeff says "!tetris"`() {
        whenSomebodySays("Jeff", "!tetris")
        thenTheBotSays("BOOM tetris for Jeff!")
    }

    @Test
    fun `Should not respond to any other messages`() {
        whenSomebodySays("John","Don't talk to me...")
        thenTheBotSaysNothing()
    }

    private fun whenSomebodySays(author: String, message: String) {
        val messageEvent = MessageEvent(message, author)
        chatEventPublishers.messages.publish(messageEvent)
    }

    private fun thenTheBotSays(message: String) {
        assert(chatService.message == message)
    }

    private fun thenTheBotSaysNothing() {
        assert(chatService.message.isEmpty())
    }

    private fun configureMocks() {
        chatService = MockChatService()
        chatEventPublishers = ChatEventPublishers()
        tetrisFeature = TetrisFeature(chatService, chatEventPublishers)
        tetrisFeature.configure()
    }

    private lateinit var chatEventPublishers: ChatEventPublishers
    private lateinit var chatService: MockChatService
    private lateinit var tetrisFeature: TetrisFeature
}