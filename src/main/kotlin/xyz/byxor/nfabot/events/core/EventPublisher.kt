package xyz.byxor.nfabot.events.core

class EventPublisher<Event> {
    fun subscribe(subscriber: EventSubscriber<Event>) {
        subscribers.add(subscriber)
    }

    fun publish(event: Event) {
        subscribers.forEach { subscriber -> subscriber.onEvent(event) }
    }

    private val subscribers: MutableList<EventSubscriber<Event>> = mutableListOf()
}