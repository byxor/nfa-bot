package xyz.byxor.nfabot.core

interface EventSubscriber<Event> {
    fun onEvent(event: Event)
}