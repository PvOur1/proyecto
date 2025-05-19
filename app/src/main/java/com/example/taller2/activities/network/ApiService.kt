package com.example.taller2.activities.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.taller2.activities.models.LoginRequest
import com.example.taller2.activities.models.LoginResponse
import com.example.taller2.activities.models.Moto
import com.example.taller2.activities.models.RegisterUser
import com.example.taller2.activities.models.RegisterUserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

        @POST("/usuario")
        fun registerUser(@Body request: RegisterUser): Call<RegisterUserResponse>

        @GET("motos/byuserid/{idUsuario}")
        fun getMotosUsuario(
            @Header("Authorization") token: String,
            @Path("idUsuario") idUsuario: Int
        ): Call<List<Moto>>
    @GET("motos/{id}")
    fun getMotoById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<Moto>

    }
