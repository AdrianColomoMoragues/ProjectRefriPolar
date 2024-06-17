package com.cambiame.models

data class Clientes(
    val id: Int,
    val nombre: String,
    val telefono: String,
    val correo: String,
    val localidad: String,
    val direccion: String,
    val caracteristicas: String
)
