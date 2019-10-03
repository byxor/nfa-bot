package xyz.byxor.nfabot.events

interface MessageSubscriber {
    fun onMessage(message: String)
}