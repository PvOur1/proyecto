package com.example.taller2.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Log.d("RegisterActivity", "onCreate ejecutado")

        val editTextNombres: EditText = findViewById(R.id.edtNombres)
        val editTextApellidos: EditText = findViewById(R.id.edtApellidos)
        val editTextCorreo: EditText = findViewById(R.id.edtCorreo)
        val editTextTelefono: EditText = findViewById(R.id.edtTelefono)
        val editTextContrasena: EditText = findViewById(R.id.edtContrasena)
        val editTextRepetirContrasena: EditText = findViewById(R.id.edtRepetirContrasena)
        val checkBoxTerminos: CheckBox = findViewById(R.id.chkTerminos)
        val buttonRegistro: Button = findViewById(R.id.btnRegistro)

        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        buttonRegistro.setOnClickListener {
            val nombres = editTextNombres.text.toString().trim()
            val apellidos = editTextApellidos.text.toString().trim()
            val correo = editTextCorreo.text.toString().trim()
            val telefono = editTextTelefono.text.toString().trim()
            val contrasena = editTextContrasena.text.toString().trim()
            val repetirContrasena = editTextRepetirContrasena.text.toString().trim()
            val aceptaTerminos = checkBoxTerminos.isChecked

            when {
                nombres.isEmpty() -> {
                    editTextNombres.error = "Ingrese sus nombres"
                    Log.d("RegisterActivity", "Nombre vacío")
                }
                apellidos.isEmpty() -> {
                    editTextApellidos.error = "Ingrese sus apellidos"
                    Log.d("RegisterActivity", "Apellidos vacíos")
                }
                correo.isEmpty() -> {
                    editTextCorreo.error = "Ingrese un correo válido"
                    Log.d("RegisterActivity", "Correo vacío")
                }
                !Patterns.EMAIL_ADDRESS.matcher(correo).matches() -> {
                    editTextCorreo.error = "Ingrese un correo válido"
                    Log.d("RegisterActivity", "Correo no válido: $correo")
                }
                telefono.isEmpty() -> {
                    editTextTelefono.error = "Ingrese un número de teléfono"
                    Log.d("RegisterActivity", "Teléfono vacío")
                }
                telefono.length < 10 -> {
                    editTextTelefono.error = "Ingrese un número de teléfono válido"
                    Log.d("RegisterActivity", "Teléfono no válido: $telefono")
                }
                contrasena.isEmpty() -> {
                    editTextContrasena.error = "Ingrese una contraseña"
                    Log.d("RegisterActivity", "Contraseña vacía")
                }
                contrasena.length < 6 -> {
                    editTextContrasena.error = "La contraseña debe tener al menos 6 caracteres"
                    Log.d("RegisterActivity", "Contraseña demasiado corta")
                }
                repetirContrasena.isEmpty() -> {
                    editTextRepetirContrasena.error = "Repita su contraseña"
                    Log.d("RegisterActivity", "Repetir contraseña vacío")
                }
                contrasena != repetirContrasena -> {
                    editTextRepetirContrasena.error = "Las contraseñas no coinciden"
                    Log.d("RegisterActivity", "Las contraseñas no coinciden")
                }
                !aceptaTerminos -> {
                    Toast.makeText(this, "Debe aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
                    Log.d("RegisterActivity", "Términos y condiciones no aceptados")
                }
                else -> {
                    val editor = sharedPreferences.edit()
                    editor.putString("nombres", nombres)
                    editor.putString("apellidos", apellidos)
                    editor.putString("correo", correo)
                    editor.putString("telefono", telefono)
                    editor.putString("contrasena", contrasena)
                    editor.apply()

                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    Log.d("RegisterActivity", "Registro exitoso para: $correo")

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("RegisterActivity", "onStart ejecutado")
    }

    override fun onResume() {
        super.onResume()
        Log.d("RegisterActivity", "onResume ejecutado")
    }

    override fun onPause() {
        super.onPause()
        Log.d("RegisterActivity", "onPause ejecutado")
    }

    override fun onStop() {
        super.onStop()
        Log.d("RegisterActivity", "onStop ejecutado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("RegisterActivity", "onDestroy ejecutado")
    }
}
