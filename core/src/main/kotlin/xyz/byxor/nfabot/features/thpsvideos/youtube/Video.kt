package xyz.byxor.nfabot.features.thpsvideos.youtube

data class Video(val id: String) {
    fun getUrl() = "https://youtu.be/$id"
}