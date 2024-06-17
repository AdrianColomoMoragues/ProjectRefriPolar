package com.cambiame.ui.features.formempleado

import com.pmdm.agenda.utilities.validacion.Validacion
import com.pmdm.agenda.utilities.validacion.ValidacionCompuesta

data class ValidacionEmpleadoUiState(
    val validacionNombre: Validacion = object : Validacion {},
    val validacionApellidos: Validacion = object : Validacion {},
    val validacionAntiguedad: Validacion = object : Validacion {},
    val validacionCorreo: Validacion = object : Validacion {},
    val validacionDireccion: Validacion = object : Validacion {},
    val validacionTelefono: Validacion = object : Validacion {},
    val validacionCodigoPostal: Validacion = object : Validacion {},
    val validacionCiudad: Validacion = object : Validacion {},
    val validacionProvincia: Validacion = object : Validacion {},
    val validacionSalario: Validacion = object : Validacion {},
) : Validacion {
    var validacionCompuesta: ValidacionCompuesta? = null

    private fun componerValidacion(): ValidacionCompuesta {
        validacionCompuesta = ValidacionCompuesta()
            .add(validacionNombre)
            .add(validacionApellidos)
            .add(validacionAntiguedad)
            .add(validacionCodigoPostal)
            .add(validacionDireccion)
            .add(validacionCorreo)
            .add(validacionTelefono)
            .add(validacionSalario)
            .add(validacionCiudad)
            .add(validacionProvincia)
        return validacionCompuesta!!
    }

    override val hayError: Boolean
        get() = validacionCompuesta?.hayError ?: componerValidacion().hayError
    override val mensajeError: String?
        get() = validacionCompuesta?.mensajeError ?: componerValidacion().mensajeError
}