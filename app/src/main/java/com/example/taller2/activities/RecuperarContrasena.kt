package com.example.taller2.activities

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R

class RecuperarContrasena : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacion_contrasena)
        Log.d("RecuperarContrasena", "onCreate ejecutado")

        val imageViewLogo: ImageView = findViewById(R.id.imgLogo)
        val textViewMensajeInstructivo: TextView = findViewById(R.id.tvMensajeInstructivo)
        val editTextCorreoElectronico: EditText = findViewById(R.id.etCorreo)
        val buttonEnviarSolicitud: Button = findViewById(R.id.btnEnviarSolicitud)

        buttonEnviarSolicitud.setOnClickListener {
            val correoIngresado = editTextCorreoElectronico.text.toString().trim()

            if (correoIngresado.isEmpty()) {
                editTextCorreoElectronico.error = "Por favor, ingrese un correo electrónico"
                Log.d("RecuperarContrasena", "Correo vacío ingresado")
            } else if (!Patterns.EMAIL_ADDRESS.matcher(correoIngresado).matches()) {
                editTextCorreoElectronico.error = "Ingrese un correo electrónico válido"
                Log.d("RecuperarContrasena", "Correo inválido ingresado: $correoIngresado")
            } else {
                Toast.makeText(
                    this,
                    "Se ha enviado un correo a $correoIngresado para restablecer la contraseña.",
                    Toast.LENGTH_LONG
                ).show()
                Log.d("RecuperarContrasena", "Correo de recuperación enviado a: $correoIngresado")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("RecuperarContrasena", "onStart ejecutado")
    }

    override fun onResume() {
        super.onResume()
        Log.d("RecuperarContrasena", "onResume ejecutado")
    }

    override fun onPause() {
        super.onPause()
        Log.d("RecuperarContrasena", "onPause ejecutado")
    }

    override fun onStop() {
        super.onStop()
        Log.d("RecuperarContrasena", "onStop ejecutado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("RecuperarContrasena", "onDestroy ejecutado")
    }
}
