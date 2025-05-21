package com.example.taller2.activities.network

import com.example.taller2.activities.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("usuario")
    fun registerUser(@Body request: RegisterUser): Call<RegisterUserResponse>

    @GET("motos/byuserid/{idUsuario}")
    fun getMotosUsuario(
        @Header("Authorization") token: String,
        @Path("idUsuario") idUsuario: Int
    ): Call<List<Moto>>

    @GET("carrito/{idUsuario}")
    fun getCarritoUsuario(
        @Header("Authorization") token: String,
        @Path("idUsuario") idUsuario: Int
    ): Call<CarritoModel>

    @GET("motos")
    fun getMotos(
        @Header("Authorization") token: String
    ): Call<List<Moto>>

    // Login con Google (envía token en el body como {"token": "el_token"})
    @POST("auth/google")
    fun sendToken(@Body tokenRequest: TokenRequest): Call<LoginResponse>
}

// Modelo para enviar token Google
data class TokenRequest(val token: String)

