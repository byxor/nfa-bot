package xyz.byxor.nfabot.features

import xyz.byxor.nfabot.chat.ChatEventPublishers
import xyz.byxor.nfabot.chat.ChatService
import xyz.byxor.nfabot.chat.MessageEvent
import xyz.byxor.nfabot.events.EventSubscriber

class ThugProPlusFeature(
        private val chatService: ChatService,
        private val chatEventPublishers: ChatEventPublishers
) :
        Feature,
        EventSubscriber<MessageEvent>
{
    private val downloadLink = "https://mega.nz/#!HG5kVSiZ!52cUsns-GZoCKis2P4JRIHkCDomJdNibnSHBcXsF8B8"

    override fun onEvent(event: MessageEvent) {
        if (event.message == "!thugproplus") {
            chatService.sendMessage("Download THUG Pro Plus today! :smile:\n$downloadLink")
        }
    }

    override fun configure() {
        chatEventPublishers.messages.subscribe(this)
    }
}