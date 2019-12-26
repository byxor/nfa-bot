package xyz.byxor.nfabot.chat

interface ChatService {
    fun sendMessage(message: String, channel: String? = null)
}