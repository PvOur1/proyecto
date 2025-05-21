package com.example.taller2.activities.models


data class MotoRequest(
    val modelo: String,
    val tipo_motor: String,
    val cilindraje: String,
    val color: String,
    val año: String,
    val precio: String,
    val descripcion: String,
    val images: String,
    val kilometraje: String,
    val combustible: String,
    val transmision: String,
    val peso: String,
    val potencia: String,
    val disponible: Boolean,
    val marca: MarcaRequest,
    val usuario: UsuarioRequest
)
