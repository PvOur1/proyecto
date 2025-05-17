package com.example.taller2.activities.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.taller2.activities.models.LoginRequest
import com.example.taller2.activities.models.LoginResponse
interface ApiService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
