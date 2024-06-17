package com.cambiame.ui.features

import com.cambiame.models.Clientes
import com.cambiame.models.Empleado
import com.cambiame.models.Encargo
import java.time.Instant

data class EncargoUiState(
    val id: Int = Instant.now().epochSecond.toInt(),
    val nombre: String = "",
    val terminada: Boolean = false,
    val idEncargado: Empleado? = null,
    val idCliente: Clientes? = null,
    val empleadosCollection: List<Empleado> = listOf<Empleado>(),
    val tipo: String = "",
    val prioridad: Int = 0,
    val porcentaje: String = ""
)

fun EncargoUiState.toEncargo() = Encargo(
    id = id,
    nombre= nombre,
    terminada = terminada,
    idEncargado = idEncargado,
    idCliente = idCliente,
    empleadosCollection = empleadosCollection,
    tipo = tipo,
    prioridad = prioridad,
    porcentaje = porcentaje.toInt()
)

fun Encargo.toEncargoUiState() = EncargoUiState(
    id = id,
    nombre =nombre,
    terminada = terminada,
    idEncargado = idEncargado,
    idCliente = idCliente,
    empleadosCollection = empleadosCollection,
    tipo = tipo,
    prioridad = prioridad,
    porcentaje = porcentaje.toString()
)