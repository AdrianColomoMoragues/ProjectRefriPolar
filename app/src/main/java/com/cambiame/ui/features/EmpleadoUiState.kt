package com.cambiame.ui.features

import com.cambiame.models.CategoriaProfesional
import com.cambiame.models.Empleado
import java.time.Instant

data class EmpleadoUiState(
    val id: Int = Instant.now().epochSecond.toInt(),
    val nombre: String = "",
    val apellidos: String = "",
    val codcategoriaProfesional: CategoriaProfesional? = null,
    val antiguedad: String = "",
    val reconocimientoMedico: Boolean = false,
    val imagen: String? = null,
    val telefono: String = "",
    val ciudad: String = "",
    val provincia: String= "",
    val direccion: String = "",
    val salario: String = "",
    val cp: String = "",
    val mail: String = ""
)

fun EmpleadoUiState.toEmpleado() = Empleado(
    id = id,
    nombre = nombre,
    apellido1 = apellidos.split(" ")[0],
    apellido2 = apellidos.split(" ")[1],
    codcategoriaProfesional = codcategoriaProfesional,
    antiguedad = antiguedad.toInt(),
    reconocimientoMedico = reconocimientoMedico,
    imagen = imagen,
    ciudad = ciudad,
    provincia = provincia,
    telefono = telefono.toInt(),
    mail = mail,
    direccion = direccion,
    cp = cp.toInt(),
    salario = salario.toDouble()
)

fun Empleado.toEmpleadoUiState() = EmpleadoUiState(
    id = id,
    nombre = nombre,
    apellidos = "${apellido1} ${apellido2}",
    codcategoriaProfesional = codcategoriaProfesional,
    antiguedad = antiguedad.toString(),
    reconocimientoMedico = reconocimientoMedico,
    imagen = imagen,
    ciudad = ciudad,
    provincia = provincia,
    telefono = telefono.toString(),
    mail = mail,
    direccion = direccion,
    cp = cp.toString(),
    salario = salario.toString()
)