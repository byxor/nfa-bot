package ut.xyz.byxor.nfabot

import org.junit.Before
import org.junit.Test
import xyz.byxor.nfabot.chat.ChatEventPublishers
import xyz.byxor.nfabot.chat.ChatService
import xyz.byxor.nfabot.chat.MessageEvent
import xyz.byxor.nfabot.features.ThugProPlusFeature

class AThugProPlusFeature {

    @Before
    fun beforeEach() = configureMocks()

    @Test
    fun `Should post a download link in the chat when somebody says "!thugproplus"`() {
        whenSomebodySays("!thugproplus")
        thenTheBotMessageContains("https://")
    }

    private fun whenSomebodySays(message: String) {
        val messageEvent = MessageEvent(message)
        chatEventPublishers.messages.publish(messageEvent)
    }

    private fun thenTheBotMessageContains(expectedMessage: String) {
        val message = chatService.message
        assert(message.contains(expectedMessage))
    }

    private fun configureMocks() {
        chatService = MockChatService()
        chatEventPublishers = ChatEventPublishers()

        thugProPlusFeature = ThugProPlusFeature(chatService, chatEventPublishers)
        thugProPlusFeature.configure()
    }

    private lateinit var chatService: MockChatService
    private lateinit var chatEventPublishers: ChatEventPublishers

    private lateinit var thugProPlusFeature: ThugProPlusFeature
}

class MockChatService : ChatService {
    var message: String = ""

    override fun sendMessage(message: String) {
        this.message = message
    }
}