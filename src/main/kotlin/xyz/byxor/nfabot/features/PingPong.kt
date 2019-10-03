package xyz.byxor.nfabot.features

import xyz.byxor.nfabot.DiscordService
import xyz.byxor.nfabot.events.core.EventSubscriber
import xyz.byxor.nfabot.events.discord.MessageEvent

class PingPong(private val discordService: DiscordService) : EventSubscriber<MessageEvent> {
    override fun onEvent(event: MessageEvent) {
        if (event.message == "Ping!") {
            discordService.sendMessage("Pong!")
        }
    }
}