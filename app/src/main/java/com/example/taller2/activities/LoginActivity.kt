package com.example.taller2.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.taller2.R
import com.example.taller2.activities.models.LoginRequest
import com.example.taller2.activities.models.LoginResponse
import com.example.taller2.activities.models.Moto
import com.example.taller2.activities.network.RetrofitClient
import com.example.taller2.activities.network.TokenRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var btnIngresar: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextCorreo: EditText = findViewById(R.id.int_name_pg3)
        val editTextContrasena: EditText = findViewById(R.id.int_psw_pg3)
        btnIngresar = findViewById(R.id.buttonIngresar)
        val tvRecuperarContrasena: TextView = findViewById(R.id.ReCuenta_pg3)
        val tvRegistrar: TextView = findViewById(R.id.tvRegistrar)
        val btnGoogleSignIn: SignInButton = findViewById(R.id.btnGoogleSignIn)

        // Configuración de Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        btnGoogleSignIn.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        btnIngresar.setOnClickListener {
            val correoIngresado = editTextCorreo.text.toString().trim()
            val contrasenaIngresada = editTextContrasena.text.toString().trim()

            if (correoIngresado.isEmpty() || contrasenaIngresada.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa correo y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isEmailValid(correoIngresado)) {
                Toast.makeText(this, "Correo no válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            btnIngresar.isEnabled = false

            val loginRequest = LoginRequest(email = correoIngresado, password = contrasenaIngresada)

            RetrofitClient.instance.login(loginRequest)
                .enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        btnIngresar.isEnabled = true

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

                            val motosJson = Gson().toJson(user.motos)
                            editor.putString("motos", motosJson)
                            editor.apply()

                            val motosGuardadasJson = sharedPreferences.getString("motos", null)
                            if (motosGuardadasJson != null) {
                                val tipoLista = object : TypeToken<List<Moto>>() {}.type
                                val listaMotos: List<Moto> = Gson().fromJson(motosGuardadasJson, tipoLista)
                                val cantidadMotos = listaMotos.size
                                editor.putInt("cantidadMotos", cantidadMotos)
                                editor.apply()
                            }

                            Toast.makeText(this@LoginActivity, "Inicio exitoso!", Toast.LENGTH_LONG).show()

                            with(sharedPreferences.edit()) {
                                putString("correo", correoIngresado)
                                putString("contrasena", contrasenaIngresada)
                                apply()
                            }

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            val errorMsg = response.errorBody()?.string() ?: "Error en credenciales"
                            Toast.makeText(this@LoginActivity, errorMsg, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        btnIngresar.isEnabled = true
                        Toast.makeText(this@LoginActivity, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }

        tvRegistrar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        tvRecuperarContrasena.setOnClickListener {
            startActivity(Intent(this, RecuperarContrasena::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)

                val idToken = account?.idToken

                if (idToken != null) {
                    // Aquí llamamos a firebaseAuthWithGoogle para autenticar con Firebase
                    firebaseAuthWithGoogle(idToken)
                } else {
                    Toast.makeText(this, "No se pudo obtener el ID token", Toast.LENGTH_SHORT).show()
                }

            } catch (e: ApiException) {
                Toast.makeText(this, "Error al iniciar con Google: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Usuario autenticado, ahora obtenemos el token Firebase
                    FirebaseAuth.getInstance().currentUser?.getIdToken(true)
                        ?.addOnCompleteListener { tokenTask ->
                            if (tokenTask.isSuccessful) {
                                val firebaseIdToken = tokenTask.result?.token
                                // Enviar token al backend para obtener token propio
                                enviarTokenAlBackend(firebaseIdToken!!)
                            } else {
                                Toast.makeText(this, "Error al obtener token Firebase", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Login Firebase fallido", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun enviarTokenAlBackend(firebaseToken: String) {
        val request = TokenRequest(firebaseToken)
        RetrofitClient.instance.sendToken(request)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        val loginResponse = response.body()!!

                        // Guardamos el token recibido del backend
                        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                        with(sharedPreferences.edit()) {
                            putString("token", loginResponse.access_token) // Token JWT de tu backend
                            putString("correo", loginResponse.user.correo)
                            putString("nombre", loginResponse.user.nombre)
                            apply()
                        }

                        // Redirigir al main
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Token inválido", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
