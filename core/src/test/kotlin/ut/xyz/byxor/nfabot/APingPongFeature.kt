package ut.xyz.byxor.nfabot;

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import xyz.byxor.nfabot.chat.ChatEventPublishers
import xyz.byxor.nfabot.chat.ChatService
import xyz.byxor.nfabot.chat.MessageEvent
import xyz.byxor.nfabot.features.Feature
import xyz.byxor.nfabot.features.PingPongFeature

class APingPongFeature {

    @Before
    fun beforeEach() = configureMocks()

    @Test
    fun `Should reply with "Pong!" when "Ping!" is said in the chat`() {
        whenSomebodySays("Ping!")
        thenTheBotSays("Pong!")
    }

    @Test
    fun `Should not reply to any other messages`() {
        whenSomebodySays("Don't talk to me...")
        thenTheBotSaysNothing()
    }

    private fun whenSomebodySays(message: String) {
        val messageEvent = MessageEvent(message, "")
        chatEventPublishers.messages.publish(messageEvent)
    }

    private fun thenTheBotSays(message: String) {
        verify(chatService).sendMessage(message)
    }

    private fun thenTheBotSaysNothing() {
        verify(chatService, never()).sendMessage(any())
    }

    private fun configureMocks() {
        chatService = mock()
        chatEventPublishers = ChatEventPublishers()
        pingPongFeature = PingPongFeature(chatService, chatEventPublishers)
        pingPongFeature.configure()
    }

    private lateinit var chatService: ChatService
    private lateinit var chatEventPublishers: ChatEventPublishers
    private lateinit var pingPongFeature: Feature
}
