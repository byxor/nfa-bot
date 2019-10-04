package ut.xyz.byxor.nfabot;

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import xyz.byxor.nfabot.core.ChatService
import xyz.byxor.nfabot.events.EventPublisher
import xyz.byxor.nfabot.chat.MessageEvent
import xyz.byxor.nfabot.features.PingPong

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
        val messageEvent = MessageEvent(message)
        messageEventPublisher.publish(messageEvent)
    }

    private fun thenTheBotSays(message: String) {
        verify(chatService).sendMessage(message)
    }

    private fun thenTheBotSaysNothing() {
        verify(chatService, never()).sendMessage(any())
    }

    private fun configureMocks() {
        chatService = mock()
        pingPongFeature = PingPong(chatService)

        messageEventPublisher = EventPublisher<MessageEvent>()
        messageEventPublisher.subscribe(pingPongFeature)
    }

    private lateinit var chatService: ChatService
    private lateinit var messageEventPublisher: EventPublisher<MessageEvent>
    private lateinit var pingPongFeature: PingPong
}
