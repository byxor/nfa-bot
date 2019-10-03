package ut.xyz.byxor.nfabot;

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import xyz.byxor.nfabot.DiscordService
import xyz.byxor.nfabot.events.DiscordEventPublisher
import xyz.byxor.nfabot.features.PingPong

class APingPongFeature {

    @Before
    fun beforeEach() = configureMocks()

    @Test
    fun `Should reply with "Pong!" when "Ping!" is said in the chat`() {
        whenSomebodySays("Ping!")
        thenTheBotSays("Pong!")
    }

    @Test
    fun `Should not reply to any other messages`() {
        whenSomebodySays("Don't talk to me...")
        thenTheBotSaysNothing()
    }

    private fun whenSomebodySays(message: String) {
        eventPublisher.pushMessageEvent(message)
    }

    private fun thenTheBotSays(message: String) {
        verify(discordService).sendMessage(message)
    }

    private fun thenTheBotSaysNothing() {
        verify(discordService, never()).sendMessage(any())
    }

    private fun configureMocks() {
        discordService = mock()
        pingPongFeature = PingPong(discordService)

        eventPublisher = DiscordEventPublisher()
        eventPublisher.subscribeToMessageEvents(pingPongFeature)
    }

    private lateinit var discordService: DiscordService
    private lateinit var eventPublisher: DiscordEventPublisher
    private lateinit var pingPongFeature: PingPong
}
