package com.example.taller2.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R
import com.example.taller2.activities.models.RegisterUser
import com.example.taller2.activities.models.RegisterUserResponse
import com.example.taller2.activities.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Log.d("RegisterActivity", "onCreate ejecutado")

        val editTextNombres: EditText = findViewById(R.id.edtNombres)
        val editTextApellidos: EditText = findViewById(R.id.edtApellidos)
        val editTextEdad: EditText = findViewById(R.id.edtEdad)
        val editTextTelefono: EditText = findViewById(R.id.edtTelefono)
        val editTextCorreo: EditText = findViewById(R.id.edtCorreo)
        val editTextDireccion: EditText = findViewById(R.id.edtDireccion)
        val editTextContrasena: EditText = findViewById(R.id.edtContrasena)
        val editTextRepetirContrasena: EditText = findViewById(R.id.edtRepetirContrasena)
        val checkBoxTerminos: CheckBox = findViewById(R.id.chkTerminos)
        val buttonRegistro: Button = findViewById(R.id.btnRegistro)

        buttonRegistro.setOnClickListener {
            val nombres = editTextNombres.text.toString().trim()
            val apellidos = editTextApellidos.text.toString().trim()
            val edad = editTextEdad.text.toString().trim()
            val telefono = editTextTelefono.text.toString().trim()
            val correo = editTextCorreo.text.toString().trim()
            val direccion = editTextDireccion.text.toString().trim()
            val contrasena = editTextContrasena.text.toString().trim()
            val repetirContrasena = editTextRepetirContrasena.text.toString().trim()
            val aceptaTerminos = checkBoxTerminos.isChecked
            val rol = "USUARIO" // Ajusta si tienes selección de rol

            when {
                nombres.isEmpty() -> {
                    editTextNombres.error = "Ingrese sus nombres"
                    Log.d("RegisterActivity", "Nombre vacío")
                }
                apellidos.isEmpty() -> {
                    editTextApellidos.error = "Ingrese sus apellidos"
                    Log.d("RegisterActivity", "Apellidos vacíos")
                }
                edad.isEmpty() -> {
                    editTextEdad.error = "Ingrese su edad"
                    Log.d("RegisterActivity", "Edad vacía")
                }
                telefono.isEmpty() -> {
                    editTextTelefono.error = "Ingrese un número de teléfono"
                    Log.d("RegisterActivity", "Teléfono vacío")
                }
                telefono.length < 10 -> {
                    editTextTelefono.error = "Ingrese un número de teléfono válido"
                    Log.d("RegisterActivity", "Teléfono no válido: $telefono")
                }
                correo.isEmpty() -> {
                    editTextCorreo.error = "Ingrese un correo válido"
                    Log.d("RegisterActivity", "Correo vacío")
                }
                !Patterns.EMAIL_ADDRESS.matcher(correo).matches() -> {
                    editTextCorreo.error = "Ingrese un correo válido"
                    Log.d("RegisterActivity", "Correo no válido: $correo")
                }
                direccion.isEmpty() -> {
                    editTextDireccion.error = "Ingrese su dirección"
                    Log.d("RegisterActivity", "Dirección vacía")
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
                    mostrarDialogoTerminos(checkBoxTerminos)
                    Log.d("RegisterActivity", "Términos y condiciones no aceptados")

                }
                else -> {
                    // Crear objeto para enviar a API
                    val registerUser = RegisterUser(
                        nombre = nombres,
                        apellido = apellidos,
                        edad = edad,
                        telefono = telefono,
                        correo = correo,
                        contraseña = contrasena,
                        direccion = direccion,
                        rol = rol
                    )
                    val checkBoxTerminos: CheckBox = findViewById(R.id.chkTerminos)
                    val txtTerminos: TextView = findViewById(R.id.txtTerminos)

                    txtTerminos.setOnClickListener {
                        mostrarDialogoTerminos(checkBoxTerminos)
                    }
                    // Llamada a API
                    val apiService = RetrofitClient.instance
                    val call = apiService.registerUser(registerUser)

                    call.enqueue(object : Callback<RegisterUserResponse> {
                        override fun onResponse(
                            call: Call<RegisterUserResponse>,
                            response: Response<RegisterUserResponse>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(this@RegisterActivity, "Registro exitoso", Toast.LENGTH_SHORT).show()
                                Log.d("RegisterActivity", "Registro exitoso para: $correo")

                                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@RegisterActivity, "Error en el registro", Toast.LENGTH_SHORT).show()
                                Log.e("RegisterActivity", "Error en registro: ${response.errorBody()?.string()}")
                            }
                        }

                        override fun onFailure(call: Call<RegisterUserResponse>, t: Throwable) {
                            Toast.makeText(this@RegisterActivity, "Fallo en la conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                            Log.e("RegisterActivity", "Error: ${t.message}")
                        }
                    })
                }
            }
        }
        val btnVolver = findViewById<Button>(R.id.btnvolver)

        btnVolver.setOnClickListener {
            finish() // Esto cierra la actividad y vuelve a la anterior
        }

    }
    private fun mostrarDialogoTerminos(checkBox: CheckBox) {
        val view = layoutInflater.inflate(R.layout.dialog_terminos, null)

        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Términos y Condiciones")
            .setView(view)
            .setPositiveButton("Aceptar") { dialog, _ ->
                checkBox.isChecked = true
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

        builder.show()
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
