package com.example.taller2.activities.models

data class CarritoItem(
    val nombre: String,
    val cantidad: Int,
    val precioUnitario: Double
) {
    val precioTotal: Double
        get() = cantidad * precioUnitario
}