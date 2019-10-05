package xyz.byxor.nfabot.chat

import net.dv8tion.jda.api.JDA

class DiscordChatService(private val discordApi: JDA) : ChatService {
    override fun sendMessage(message: String) {

    }
}