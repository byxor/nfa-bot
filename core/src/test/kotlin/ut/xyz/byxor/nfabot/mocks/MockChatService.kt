package ut.xyz.byxor.nfabot.mocks

import xyz.byxor.nfabot.chat.ChatService

class MockChatService : ChatService {
    var message: String = ""
    var channel: String = ""

    override fun sendMessage(message: String, channel: String?) {
        this.message = message
        this.channel = channel ?: this.channel
    }
}