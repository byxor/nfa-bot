package xyz.byxor.nfabot.features

import xyz.byxor.nfabot.core.ChatService
import xyz.byxor.nfabot.core.EventSubscriber
import xyz.byxor.nfabot.discord.events.MessageEvent

class PingPong(private val chatService: ChatService) : EventSubscriber<MessageEvent> {
    override fun onEvent(event: MessageEvent) {
        if (event.message == "Ping!") {
            chatService.sendMessage("Pong!")
        }
    }
}