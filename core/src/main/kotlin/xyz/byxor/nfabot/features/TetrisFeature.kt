package xyz.byxor.nfabot.features

import xyz.byxor.nfabot.chat.ChatEventPublishers
import xyz.byxor.nfabot.chat.ChatService
import xyz.byxor.nfabot.chat.MessageEvent
import xyz.byxor.nfabot.events.EventSubscriber

class TetrisFeature(
        private val chatService: ChatService,
        private val chatEventPublishers: ChatEventPublishers
) : Feature, EventSubscriber<MessageEvent>
{
    override fun configure() {
        chatEventPublishers.messages.subscribe(this)
    }

    override fun onEvent(event: MessageEvent) {
        if (event.message == "!tetris") {
            chatService.sendMessage("BOOM tetris for ${event.author}!")
        }
    }
}