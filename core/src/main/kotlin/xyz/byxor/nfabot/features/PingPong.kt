package xyz.byxor.nfabot.features

import xyz.byxor.nfabot.chat.ChatService
import xyz.byxor.nfabot.events.EventSubscriber
import xyz.byxor.nfabot.chat.MessageEvent

class PingPong(private val chatService: ChatService) : EventSubscriber<MessageEvent> {
    override fun onEvent(event: MessageEvent) {
        if (event.message == "Ping!") {
            chatService.sendMessage("Pong!")
        }
    }
}