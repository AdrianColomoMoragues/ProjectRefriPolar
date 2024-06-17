package com.cambiame.ui.features.formcliente

import com.pmdm.agenda.utilities.validacion.Validacion
import com.pmdm.agenda.utilities.validacion.ValidacionCompuesta

data class ValidacionClienteUiState(
    val validacionNombre: Validacion = object : Validacion {},
    val validacionCaracteristicas: Validacion = object : Validacion {},
    val validacionDireccion: Validacion = object : Validacion {},
    val validacionLocalidad: Validacion = object : Validacion {},
    val validacionCorreo: Validacion = object : Validacion {},
    val validacionTelefono: Validacion = object : Validacion {}
) : Validacion {
    private var validacionCompuesta: ValidacionCompuesta? = null

    private fun componerValidacion(): ValidacionCompuesta {
        validacionCompuesta = ValidacionCompuesta()
            .add(validacionNombre)
            .add(validacionCaracteristicas)
            .add(validacionDireccion)
            .add(validacionLocalidad)
            .add(validacionCorreo)
            .add(validacionTelefono)
        return validacionCompuesta!!
    }

    // Puesto que la validación se compone de otras validaciones,
    // delegamos en la validación compuesta. Que calculamos en el momento del acceso.
    override val hayError: Boolean
        get() = validacionCompuesta?.hayError ?: componerValidacion().hayError
    override val mensajeError: String?
//        get() = "Revisa los datos del contacto"
        get() = validacionCompuesta?.mensajeError ?: componerValidacion().mensajeError
}
