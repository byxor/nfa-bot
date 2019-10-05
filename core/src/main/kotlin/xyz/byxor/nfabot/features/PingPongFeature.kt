package xyz.byxor.nfabot.features

import xyz.byxor.nfabot.chat.ChatEventPublishers
import xyz.byxor.nfabot.chat.ChatService
import xyz.byxor.nfabot.events.EventSubscriber
import xyz.byxor.nfabot.chat.MessageEvent

class PingPongFeature(
        private val chatService: ChatService,
        private val chatEventPublishers: ChatEventPublishers
) :
        Feature,
        EventSubscriber<MessageEvent>
{
    override fun onEvent(event: MessageEvent) {
        if (event.message == "Ping!") {
            chatService.sendMessage("Pong!")
        }
    }

    override fun configure() {
        chatEventPublishers.messages.subscribe(this)
    }
}