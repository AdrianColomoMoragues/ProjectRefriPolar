package com.cambiame.ui.features

import com.cambiame.models.Clientes
import java.time.Instant

data class ClienteUiState(
    val id: Int = Instant.now().epochSecond.toInt(),
    val nombre: String = "",
    val telefono: String = "",
    val correo: String = "",
    val localidad: String = "",
    val direccion: String = "",
    val caracteristicas: String = ""
)

fun ClienteUiState.toCliente() = Clientes(
    id = id,
    nombre = nombre,
    telefono = telefono,
    correo = correo,
    localidad = localidad,
    direccion = direccion,
    caracteristicas = caracteristicas
)

fun Clientes.toClienteUiState() = ClienteUiState(
    id = id,
    nombre = nombre,
    telefono = telefono,
    correo = correo,
    localidad = localidad,
    direccion = direccion,
    caracteristicas = caracteristicas
)