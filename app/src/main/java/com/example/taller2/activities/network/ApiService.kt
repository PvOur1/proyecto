package com.example.taller2.activities.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.taller2.activities.models.LoginRequest
import com.example.taller2.activities.models.LoginResponse
import com.example.taller2.activities.models.RegisterUser
import com.example.taller2.activities.models.RegisterUserResponse

interface ApiService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("/usuario")
    fun registerUser(@Body request: RegisterUser): Call<RegisterUserResponse>
}
