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
import com.example.taller2.activities.models.LoginRequest
import com.example.taller2.activities.models.LoginResponse
import com.example.taller2.activities.models.Moto
import com.example.taller2.activities.network.RetrofitClient
import com.example.taller2.fragments.PerfilFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextCorreo: EditText = findViewById(R.id.int_name_pg3)
        val editTextContrasena: EditText = findViewById(R.id.int_psw_pg3)
        val btnIngresar: Button = findViewById(R.id.buttonIngresar)
        val tvRecuperarContrasena: TextView = findViewById(R.id.ReCuenta_pg3)
        val tvRegistrar: TextView = findViewById(R.id.tvRegistrar)

        btnIngresar.setOnClickListener {
            val correoIngresado = editTextCorreo.text.toString().trim()
            val contrasenaIngresada = editTextContrasena.text.toString().trim()

            if (correoIngresado.isEmpty() || contrasenaIngresada.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa correo y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val loginRequest = LoginRequest(email = correoIngresado, password = contrasenaIngresada)

            RetrofitClient.instance.login(loginRequest)
                .enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful && response.body() != null) {

                            val loginResponse = response.body()!!
                            val user = loginResponse.user

                            val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("token", loginResponse.access_token)
                            editor.putInt("idUsuario", user.id)
                            editor.putString("nombre", user.nombre)
                            editor.putString("apellido", user.apellido)
                            editor.putString("edad", user.edad)
                            editor.putString("telefono", user.telefono)
                            editor.putString("correo", user.correo)
                            editor.putString("direccion", user.direccion)
                            editor.putString("rol", user.rol)

                            // Convertir y guardar lista de motos como JSON
                            val motosJson = Gson().toJson(user.motos)
                            editor.putString("motos", motosJson)
                            editor.apply()

                            // Leer motos y contarlas
                            val motosGuardadasJson = sharedPreferences.getString("motos", null)
                            if (motosGuardadasJson != null) {
                                val tipoLista = object : TypeToken<List<Moto>>() {}.type
                                val listaMotos: List<Moto> = Gson().fromJson(motosGuardadasJson, tipoLista)



                                val cantidadMotos = listaMotos.size
                                editor.putInt("cantidadMotos", cantidadMotos)
                                editor.apply()



                            }

                            Toast.makeText(this@LoginActivity, "Inicio exitoso! Token: ${loginResponse.access_token}", Toast.LENGTH_LONG).show()

                            // Guardar correo y contraseña por aparte
                            with(sharedPreferences.edit()) {
                                putString("correo", correoIngresado)
                                putString("contrasena", contrasenaIngresada)
                                apply()
                            }

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(this@LoginActivity, "Error en credenciales", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }

        tvRegistrar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        tvRecuperarContrasena.setOnClickListener {
            val intent = Intent(this, RecuperarContrasena::class.java)
            startActivity(intent)
        }
    }
}
