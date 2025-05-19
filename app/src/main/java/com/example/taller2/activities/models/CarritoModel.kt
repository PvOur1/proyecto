package com.example.taller2.activities.models

data class CarritoModel(
    val id: Int,
    val fecha_creacion: String,
    val fecha_actualizacion: String,
    val motos: List<Moto>
)
