package xyz.byxor.nfabot.features

import xyz.byxor.nfabot.DiscordService
import xyz.byxor.nfabot.events.MessageSubscriber

class PingPong(private val discordService: DiscordService) : MessageSubscriber {
    override fun onMessage(message: String) {
        if (message.equals("Ping!")) {
            discordService.sendMessage("Pong!")
        }
    }
}