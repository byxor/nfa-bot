package xyz.byxor.nfabot.events.core

interface EventSubscriber<Event> {
    fun onEvent(event: Event)
}