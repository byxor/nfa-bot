package xyz.byxor.nfabot.chat

data class MessageEvent(
        val message: String,
        val author: String,
        val channel: String = "general"
)