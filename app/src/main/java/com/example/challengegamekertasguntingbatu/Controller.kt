package com.example.challengegamekertasguntingbatu

class Controller {

    private val draw = 0
    private val paper = 1
    private val scissor = 2
    private val rock = 3

    fun gameRule(hand1: Int, hand2: Int): Int {

        val result = when {
            hand1 == paper && hand2 == scissor || hand1 == scissor && hand2 == paper -> scissor
            hand1 == scissor && hand2 == rock || hand1 == rock && hand2 == scissor -> rock
            hand1 == rock && hand2 == paper || hand1 == paper && hand2 == rock -> paper
            else -> draw
        }
        return result
    }

    fun showWinner(result: Int): String {

        val resultText = when (result) {
            1 -> "Kamu Menang!"
            2 -> "COM Menang!"
            else -> "Seri!"
        }
        return resultText
    }
}