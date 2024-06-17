package com.cambiame.models

data class Encargo(
    val id: Int,
    val nombre: String,
    val terminada: Boolean,
    val idEncargado: Empleado?,
    val idCliente: Clientes?,
    val empleadosCollection: List<Empleado>,
    val tipo: String,
    val prioridad: Int,
    val porcentaje: Int
)
