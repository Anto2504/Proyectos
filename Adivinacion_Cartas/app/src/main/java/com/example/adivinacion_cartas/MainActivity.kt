package com.example.adivinacion_cartas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var playerName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.startButton)

        startButton.setOnClickListener {
            val playerNameEditText = findViewById<EditText>(R.id.playerNameEditText)
            playerName = playerNameEditText.text.toString().trim()
            if (playerName.isEmpty()) {
                showSnackbar("Por favor, introduce un nombre")
            } else {
                showWelcomeScreen()
            }
        }
    }

    private fun showSnackbar(message: String) {
        val playerNameEditText = findViewById<EditText>(R.id.playerNameEditText)
        Snackbar.make(playerNameEditText, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun showWelcomeScreen() {
        val welcomeMessage = "Bienvenido, $playerName"
        val playerNameEditText = findViewById<EditText>(R.id.playerNameEditText)
        Snackbar.make(playerNameEditText, welcomeMessage, Snackbar.LENGTH_SHORT)
            .setAction("OK") { startGame() }
            .show()
    }

    private fun startGame() {
        setContentView(R.layout.activity_game)
        val cardImageView = findViewById<ImageView>(R.id.cardImageView)

        val pointsTextView = findViewById<TextView>(R.id.pointsTextView)
        var puntos = 0

        val cartas = mutableListOf(
            "c1", "c2", "c3", "c4", "c5",
            "c6", "c7", "c8", "c9", "c10",
            "c11", "c12", "c13"
        )
        var cartaActual = obtenerCartaAleatoria(cartas)

        val upButton = findViewById<Button>(R.id.upImage)
        val downButton = findViewById<Button>(R.id.downImage)

        upButton.setOnClickListener {
            val cartaFutura = obtenerCartaAleatoria(cartas)
            if (cartaFutura != cartaActual) {
                cartaActual = cartaFutura
                val resourceId = resources.getIdentifier(cartaActual, "drawable", packageName)
                cardImageView.setImageResource(resourceId)
                puntos++
            } else {
                showSnackbar("Misma carta, ningún punto ganado.")
            }
            pointsTextView.text = "Puntos: $puntos"
        }

        downButton.setOnClickListener {
            val cartaFutura = obtenerCartaAleatoria(cartas)
            if (cartaFutura != cartaActual) {
                cartaActual = cartaFutura
                val resourceId = resources.getIdentifier(cartaActual, "drawable", packageName)
                cardImageView.setImageResource(resourceId)
                puntos++
            } else {
                showSnackbar("Misma carta, ningún punto ganado.")
            }
            pointsTextView.text = "Puntos: $puntos"
        }
    }

    private fun obtenerCartaAleatoria(cartas: MutableList<String>): String {
        val indice = Random.nextInt(cartas.size)
        return cartas.removeAt(indice)
    }
}
