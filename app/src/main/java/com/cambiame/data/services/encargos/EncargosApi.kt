package com.cambiame.data.services.encargos

import com.cambiame.models.Clientes
import com.cambiame.models.Empleado

data class EncargosApi(
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
