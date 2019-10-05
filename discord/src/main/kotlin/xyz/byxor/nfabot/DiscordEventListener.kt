package xyz.byxor.nfabot

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import xyz.byxor.nfabot.chat.ChatEventPublishers
import xyz.byxor.nfabot.chat.MessageEvent

class DiscordEventListener(
        private val chatEventPublishers: ChatEventPublishers
) : ListenerAdapter() {

    override fun onMessageReceived(discordEvent: MessageReceivedEvent) {
        val messageAuthor = discordEvent.message.author.name
        val messageText = discordEvent.message.contentRaw
        println("$messageAuthor: $messageText")

        val messageEvent = MessageEvent(messageText, messageAuthor)
        chatEventPublishers.messages.publish(messageEvent)
    }
}