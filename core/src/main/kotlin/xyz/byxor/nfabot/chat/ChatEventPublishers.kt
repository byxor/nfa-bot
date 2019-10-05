package xyz.byxor.nfabot.chat

import xyz.byxor.nfabot.events.EventPublisher

class ChatEventPublishers {
    val messages = EventPublisher<MessageEvent>()
}