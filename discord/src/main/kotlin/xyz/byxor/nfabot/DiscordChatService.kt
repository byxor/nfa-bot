package xyz.byxor.nfabot

import net.dv8tion.jda.api.JDA
import xyz.byxor.nfabot.chat.ChatService

class DiscordChatService(private val discordApi: JDA) : ChatService {

    init {
        discordApi.awaitReady()
    }

    override fun sendMessage(message: String, channel: String?) {
        val channelObject = when(channel) {
            null -> getChannel("general")
            else -> getChannel(channel)
        }

        channelObject.sendMessage(message).queue()
    }

    private fun getChannel(name: String) = discordApi.getTextChannelsByName(name, true)[0]
}