package xyz.byxor.nfabot

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.TextChannel
import xyz.byxor.nfabot.chat.ChatService

class DiscordChatService(private val discordApi: JDA) : ChatService {

    private val generalChannel: TextChannel

    init {
        discordApi.awaitReady()
        generalChannel = discordApi.getTextChannelsByName("general", true)[0]
    }

    override fun sendMessage(message: String) {
        generalChannel.sendMessage(message).queue()
    }


}