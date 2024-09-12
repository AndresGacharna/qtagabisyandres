package com.example.qta

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val valorPropiedad: EditText = findViewById(R.id.valor_propiedad)
        val cuantoNecesitas: EditText = findViewById(R.id.cuanto_necesitas)
        val plazoCredito: EditText = findViewById(R.id.plazo_credito)
        val tasaInteres: EditText = findViewById(R.id.tasa_interes)
        val botonSimular: Button = findViewById(R.id.boton_simular)
        val resultado: TextView = findViewById(R.id.resultado)

        botonSimular.setOnClickListener {
            val valorPropiedadText = valorPropiedad.text.toString()
            val cuantoNecesitasText = cuantoNecesitas.text.toString()
            val plazoCreditoText = plazoCredito.text.toString()
            val tasaInteresText = tasaInteres.text.toString()

            val valorPropiedad = valorPropiedadText.toLongOrNull()
            val cuantoNecesitas = cuantoNecesitasText.toLongOrNull()
            val plazoCredito = plazoCreditoText.toIntOrNull()
            val tasaInteres = tasaInteresText.toDoubleOrNull()

            if (valorPropiedad == null || valorPropiedad < 70000000) {
                resultado.text = "El valor de la propiedad debe ser al menos $70,000,000."
                return@setOnClickListener
            }

            if (cuantoNecesitas == null || cuantoNecesitas < 50000000 || cuantoNecesitas > (valorPropiedad * 0.7)) {
                resultado.text = "El préstamo debe ser al menos $50,000,000 y no exceder el 70% del valor de la propiedad."
                return@setOnClickListener
            }

            if (plazoCredito == null || plazoCredito < 5 || plazoCredito > 20) {
                resultado.text = "El plazo del crédito debe estar entre 5 y 20 años."
                return@setOnClickListener
            }

            if (tasaInteres == null || tasaInteres < 12.0 || tasaInteres > 24.9) {
                resultado.text = "La tasa de interés debe estar entre 12.0% y 24.9%."
                return@setOnClickListener
            }

            // Realizar el cálculo de la cuota
            val valorPrestamo = cuantoNecesitas.toDouble()
            val tasaMensual = (tasaInteres / 12) / 100
            val plazoMeses = plazoCredito * 12

            val cuota = valorPrestamo * (tasaMensual * (1 + tasaMensual).pow(plazoMeses)) / ((1 + tasaMensual).pow(plazoMeses) - 1)

            // Mostrar el resultado
            resultado.text = "Paga cuotas de $%.2f por mes".format(cuota)
        }
    }
}
