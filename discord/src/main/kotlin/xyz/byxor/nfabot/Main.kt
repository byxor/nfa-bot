package xyz.byxor.nfabot

import net.dv8tion.jda.api.JDABuilder
import xyz.byxor.nfabot.chat.ChatEventPublishers
import xyz.byxor.nfabot.features.PingPongFeature

fun main(args: Array<String>) {
    val token = args[0]

    val chatEventPublishers = ChatEventPublishers()

    val discordEventListener = DiscordEventListener(chatEventPublishers)

    val discordApi =
            JDABuilder(token)
            .addEventListeners(discordEventListener)
            .build()

    val discordChatService = DiscordChatService(discordApi)

    val features = listOf(
            PingPongFeature(discordChatService, chatEventPublishers)
    )

    features.forEach {
        feature -> feature.configure()
    }
}
