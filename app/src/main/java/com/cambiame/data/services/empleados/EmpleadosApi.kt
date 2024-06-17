package com.cambiame.data.services.empleados

import com.cambiame.models.CategoriaProfesional

data class EmpleadosApi(
    val id: Int,
    val nombre: String,
    val apellido1: String,
    val apellido2: String,
    val codcategoriaProfesional: CategoriaProfesional?,
    val antiguedad: Int,
    val reconocimientoMedico: Boolean,
    val imagen: String?,
    val telefono: Int,
    val ciudad: String,
    val provincia: String,
    val direccion: String,
    val salario: Double,
    val cp: Int,
    val mail: String
)
