package com.example.taller2.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R
import java.text.SimpleDateFormat
import java.util.*

class CentralActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_central)


        val edtPeso = findViewById<EditText>(R.id.edtPeso)
        val edtTemperatura = findViewById<EditText>(R.id.edtTemperatura)
        val edtConsumoLiquidos = findViewById<EditText>(R.id.edtConsumoLiquidos)
        val spinnerActividad = findViewById<Spinner>(R.id.spinnerActividad)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)


        val actividades = arrayOf("Sedentario", "Moderado", "Activo")
        val factoresActividad = mapOf("Sedentario" to 1.0, "Moderado" to 1.2, "Activo" to 1.5)


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, actividades)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerActividad.adapter = adapter


        btnCalcular.setOnClickListener {
            val peso = edtPeso.text.toString().toDoubleOrNull()
            val temperatura = edtTemperatura.text.toString().toDoubleOrNull()
            val consumoActual = edtConsumoLiquidos.text.toString().toDoubleOrNull()
            val actividad = spinnerActividad.selectedItem.toString()

            if (peso == null || temperatura == null || consumoActual == null) {
                Toast.makeText(this, "Por favor ingrese todos los datos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val factorActividad = factoresActividad[actividad] ?: 1.0
            val factorTemperatura = if (temperatura > 25) 1.2 else 1.0


            val necesidadDiaria = peso * 30 * factorActividad * factorTemperatura
            val deficit = necesidadDiaria - consumoActual


            val resultado = """
                Necesidad diaria: ${String.format("%.2f", necesidadDiaria)} ml
                ${if (deficit > 0) "Déficit: ${String.format("%.2f", deficit)} ml" else "Exceso: ${String.format("%.2f", -deficit)} ml"}
                Plan recomendado: ${generarPlanConsumo(necesidadDiaria)}
            """.trimIndent()

            tvResultado.text = resultado
        }


        btnGuardar.setOnClickListener {
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val fecha = sdf.format(Date())
            val resultado = tvResultado.text.toString()


            Toast.makeText(this, "Resultados guardados: $fecha", Toast.LENGTH_SHORT).show()
        }
    }


    private fun generarPlanConsumo(necesidadDiaria: Double): String {
        val porciones = listOf(0.3, 0.2, 0.2, 0.2, 0.1)
        val horas = listOf("8 AM", "11 AM", "2 PM", "5 PM", "8 PM")
        val plan = StringBuilder("\n")

        for (i in porciones.indices) {
            val cantidad = necesidadDiaria * porciones[i]
            plan.append("${horas[i]} - ${String.format("%.2f", cantidad)} ml\n")
        }
        return plan.toString()
    }
}
