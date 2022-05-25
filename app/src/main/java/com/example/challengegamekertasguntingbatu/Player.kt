package com.example.challengegamekertasguntingbatu

class Player(private var name: String) {

    var handId: Int = 0
    lateinit var hand: String

    fun showLogPlayer(): String {

        this.hand = getHandName()
        return "${this.name} Choose ${this.hand}"
    }

    private fun getHandName(): String {

        val handName = when (handId) {
            1 -> "Paper"
            2 -> "Scissor"
            3 -> "Rock"
            else -> "None"
        }
        return handName
    }

}