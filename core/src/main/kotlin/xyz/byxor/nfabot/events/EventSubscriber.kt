package xyz.byxor.nfabot.events

interface EventSubscriber<Event> {
    fun onEvent(event: Event)
}