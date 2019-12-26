package ut.xyz.byxor.nfabot;

import org.junit.Before
import org.junit.Test
import ut.xyz.byxor.nfabot.mocks.MockChatService
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
        assert(chatService.message == message)
    }

    private fun thenTheBotSaysNothing() {
        assert(chatService.message.isEmpty())
    }

    private fun configureMocks() {
        chatService = MockChatService()
        chatEventPublishers = ChatEventPublishers()
        pingPongFeature = PingPongFeature(chatService, chatEventPublishers)
        pingPongFeature.configure()
    }

    private lateinit var chatService: MockChatService
    private lateinit var chatEventPublishers: ChatEventPublishers
    private lateinit var pingPongFeature: Feature
}
