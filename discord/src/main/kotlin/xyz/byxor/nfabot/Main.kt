package xyz.byxor.nfabot

import net.dv8tion.jda.api.JDABuilder
import xyz.byxor.nfabot.chat.ChatEventPublishers
import xyz.byxor.nfabot.features.Feature
import xyz.byxor.nfabot.features.PingPongFeature
import xyz.byxor.nfabot.features.TetrisFeature
import xyz.byxor.nfabot.features.ThugProPlusFeature
import xyz.byxor.nfabot.features.thpsvideos.ThpsVideosFeature
import xyz.byxor.nfabot.features.thpsvideos.youtube.v3.YoutubeApiV3
import xyz.byxor.nfabot.random.RandomImplementation

fun main(args: Array<String>) {
    val token = args[0]

    val chatEventPublishers = ChatEventPublishers()

    val discordEventListener = DiscordEventListener(chatEventPublishers)

    val discordApi =
            JDABuilder(token)
            .addEventListeners(discordEventListener)
            .build()

    val discordChatService = DiscordChatService(discordApi)

    val random = RandomImplementation()
    val youtubeApi = YoutubeApiV3()

    val features = listOf<Feature>(
            PingPongFeature(discordChatService, chatEventPublishers),
            ThugProPlusFeature(discordChatService, chatEventPublishers),
            TetrisFeature(discordChatService, chatEventPublishers),
            ThpsVideosFeature(discordChatService, chatEventPublishers, random, youtubeApi)
    )

    features.forEach {
        feature -> feature.configure()
    }
}
