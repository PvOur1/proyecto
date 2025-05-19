package com.example.taller2.activities.models

data class User (
    val id : Int,
    val nombre : String,
    val apellido : String,
    val edad : String,
    val telefono : String,
    val correo : String,
    val direccion : String,
    val rol : String,
    val motos : List<Moto>

)