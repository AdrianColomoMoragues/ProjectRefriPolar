package com.cambiame.data.services.clientes

data class ClientesApi(
    val id: Int,
    val nombre: String,
    val telefono: String,
    val correo: String,
    val localidad: String,
    val direccion: String,
    val caracteristicas: String
)
