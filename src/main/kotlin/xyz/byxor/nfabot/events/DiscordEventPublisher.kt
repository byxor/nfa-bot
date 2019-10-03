package xyz.byxor.nfabot.events

class DiscordEventPublisher {
    fun subscribeToMessageEvents(messageSubscriber: MessageSubscriber) {
        messageSubscribers.add(messageSubscriber)
    }

    fun pushMessageEvent(message: String) {
        messageSubscribers.forEach { subscriber -> subscriber.onMessage(message) }
    }

    private val messageSubscribers: MutableList<MessageSubscriber> = mutableListOf()
}