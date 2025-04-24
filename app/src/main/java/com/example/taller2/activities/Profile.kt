package com.example.taller2.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d("ProfileActivity", "onCreate ejecutado")

        val nombresTextView = findViewById<TextView>(R.id.tvNombres)
        val apellidosTextView = findViewById<TextView>(R.id.tvApellidos)
        val correoTextView = findViewById<TextView>(R.id.tvCorreo)
        val telefonoTextView = findViewById<TextView>(R.id.tvTelefono)

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val nombres = sharedPreferences.getString("nombres", "juan")
        val apellidos = sharedPreferences.getString("apellidos", "hernandez")
        val correo = sharedPreferences.getString("correo", "No registrado")
        val telefono = sharedPreferences.getString("telefono", "3105853456")

        nombresTextView.text = nombres
        apellidosTextView.text = apellidos
        correoTextView.text = correo
        telefonoTextView.text = telefono

        val btnEditar = findViewById<Button>(R.id.btnEditar)
        val btnCentral = findViewById<Button>(R.id.actividad_central)

        btnCentral.setOnClickListener {
            Log.d("LoginActivity", "Navegando a CentralActivity")
            val intent = Intent(this, CentralActivity::class.java)
            startActivity(intent)
        }
        btnEditar.setOnClickListener {
            Log.d("ProfileActivity", "Botón Editar presionado")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("ProfileActivity", "onStart ejecutado")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ProfileActivity", "onResume ejecutado")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ProfileActivity", "onPause ejecutado")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ProfileActivity", "onStop ejecutado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ProfileActivity", "onDestroy ejecutado")
    }
}
