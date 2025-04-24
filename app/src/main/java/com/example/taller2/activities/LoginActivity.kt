package com.example.taller2.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d("LoginActivity", "onCreate ejecutado")

        val editTextCorreo: EditText = findViewById(R.id.int_name_pg3)
        val editTextContrasena: EditText = findViewById(R.id.int_psw_pg3)
        val btnIngresar: Button = findViewById(R.id.buttonIngresar)
        val tvRecuperarContrasena: TextView = findViewById(R.id.ReCuenta_pg3)
        val tvRegistrar: TextView = findViewById(R.id.tvRegistrar)

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val correoGuardado = sharedPreferences.getString("correo", "")
        val contrasenaGuardada = sharedPreferences.getString("contrasena", "")

        btnIngresar.setOnClickListener {
            val correoIngresado = editTextCorreo.text.toString().trim()
            val contrasenaIngresada = editTextContrasena.text.toString().trim()

            if (correoIngresado == correoGuardado && contrasenaIngresada == contrasenaGuardada) {
                Log.d("LoginActivity", "Inicio de sesión exitoso")
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Log.d("LoginActivity", "Correo o contraseña incorrectos")
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegistrar.setOnClickListener {
            Log.d("LoginActivity", "Navegando a RegisterActivity")
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        tvRecuperarContrasena.setOnClickListener {
            Log.d("LoginActivity", "Navegando a RecuperarContrasena")
            val intent = Intent(this, RecuperarContrasena::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LoginActivity", "onStart ejecutado")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LoginActivity", "onResume ejecutado")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LoginActivity", "onPause ejecutado")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LoginActivity", "onStop ejecutado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LoginActivity", "onDestroy ejecutado")
    }
}
