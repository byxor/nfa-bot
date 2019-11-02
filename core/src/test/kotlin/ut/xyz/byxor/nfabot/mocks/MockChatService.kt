package ut.xyz.byxor.nfabot.mocks

import xyz.byxor.nfabot.chat.ChatService

class MockChatService : ChatService {
    var message: String = ""

    override fun sendMessage(message: String) {
        this.message = message
    }
}