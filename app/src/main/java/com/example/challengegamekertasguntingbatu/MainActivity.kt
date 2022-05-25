package com.example.challengegamekertasguntingbatu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var handPaper1: ImageView
    private lateinit var handScissor1: ImageView
    private lateinit var handRock1: ImageView
    private lateinit var handPaper2: ImageView
    private lateinit var handScissor2: ImageView
    private lateinit var handRock2: ImageView
    private lateinit var viewResult: ImageView
    private lateinit var textRefresh: TextView
    private lateinit var logPlayer: String

    private val player1 = Player("Player 1")
    private val player2 = Player("Player 2")
    private val controller = Controller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handPaper1 = findViewById(R.id.iv_paper_p1)
        handScissor1 = findViewById(R.id.iv_scissor_p1)
        handRock1 = findViewById(R.id.iv_rock_p1)
        handPaper2 = findViewById(R.id.iv_paper_p2)
        handScissor2 = findViewById(R.id.iv_scissor_p2)
        handRock2 = findViewById(R.id.iv_rock_p2)
        viewResult = findViewById(R.id.iv_result)
        textRefresh = findViewById(R.id.tv_message)
        val ivRefresh = findViewById<ImageView>(R.id.iv_refresh)

        setOnClick(handPaper1, 1)
        setOnClick(handScissor1, 2)
        setOnClick(handRock1, 3)

        ivRefresh.setOnClickListener {
            clearView()
        }
    }

    private fun setOnClick(view: ImageView, idView: Int) {

        view.setOnClickListener {

            isHandEnabled(false)
            setPlayer1(view, idView)
            setPlayer2()
            startGame()
        }
    }

    private fun isHandEnabled(enable: Boolean) {

        handPaper1.isEnabled = enable
        handScissor1.isEnabled = enable
        handRock1.isEnabled = enable
    }

    private fun setPlayer1(view: ImageView, handId: Int) {

        player1.handId = handId
        setHand(view, handId, true)
        logPlayer = player1.showLogPlayer()
        logD(logPlayer)
    }

    private fun setPlayer2() {

        player2.handId = (1..3).random()
        when (player2.handId) {
            1 -> setHand(handPaper2, 1, true)
            2 -> setHand(handScissor2, 2, true)
            3 -> setHand(handRock2, 3, true)
        }
        logPlayer = player2.showLogPlayer()
        logD(logPlayer)
    }

    private fun setHand(view: ImageView, handId: Int, isSelected: Boolean) {

        view.setImageResource(setHandImage(handId))
        when (isSelected) {
            true -> view.setBackgroundResource(R.drawable.ic_tangan_background)
            false -> view.setBackgroundResource(0)
        }
    }

    private fun setHandImage(handImage: Int): Int {

        return when (handImage) {
            1 -> R.drawable.ic_hand_paper
            2 -> R.drawable.ic_hand_scissor
            3 -> R.drawable.ic_hand_rock
            else -> 0
        }
    }

    private fun startGame() {

        val idPlayer1 = player1.handId
        val handPlayer1 = player1.hand
        val idPlayer2 = player2.handId
        val handPlayer2 = player2.hand
        val resultGame = controller.gameRule(idPlayer1, idPlayer2)

        logD("Start Game = $handPlayer1 VS $handPlayer2")
        if (idPlayer1 == resultGame) {
            showImageResult(1)
            return
        }
        if (idPlayer2 == resultGame) {
            showImageResult(2)
            return
        }
        showImageResult(resultGame)
    }

    private fun showImageResult(result: Int) {

        val getTextResult = controller.showWinner(result)
        viewResult.setImageResource(setImageResult(result))
        textRefresh.text = resources.getText(R.string.refresh_game)
        Toast.makeText(this, getTextResult, Toast.LENGTH_SHORT).show()
        logD("Result = $getTextResult")
    }


    private fun setImageResult(resultImage: Int): Int {

        return when (resultImage) {
            0 -> R.drawable.ic_result_seri
            1 -> R.drawable.ic_result_kamu
            2 -> R.drawable.ic_result_com
            else -> R.drawable.image_vs
        }
    }

    private fun clearView() {

        isHandEnabled(true)
        viewResult.setImageResource(setImageResult(3))
        textRefresh.text = resources.getText(R.string.input_hand)
        clearViewPlayer1()
        clearViewPlayer2()
        logD("Button Refresh Clicked")
    }

    private fun clearViewPlayer1() {

        setHand(handPaper1, 1, false)
        setHand(handScissor1, 2, false)
        setHand(handRock1, 3, false)
    }

    private fun clearViewPlayer2() {

        setHand(handPaper2, 1, false)
        setHand(handScissor2, 2, false)
        setHand(handRock2, 3, false)
    }

    private fun logD(message: String) {

        Log.i(MainActivity::class.java.simpleName, message)
    }

}