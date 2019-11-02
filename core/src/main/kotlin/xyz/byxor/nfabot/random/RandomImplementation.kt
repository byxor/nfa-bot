package xyz.byxor.nfabot.random

class RandomImplementation : Random {
    override fun index(numberOfElements: Int) = (0 until numberOfElements).random()
}