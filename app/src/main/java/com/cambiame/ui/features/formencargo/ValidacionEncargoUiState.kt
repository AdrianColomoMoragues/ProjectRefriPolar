package com.cambiame.ui.features.formencargo

import com.pmdm.agenda.utilities.validacion.Validacion
import com.pmdm.agenda.utilities.validacion.ValidacionCompuesta

class ValidacionEncargoUiState(
    val validacionNombre: Validacion = object : Validacion {},
    val validacionPorcentaje: Validacion = object : Validacion {},
) : Validacion {
    private var validacionCompuesta: ValidacionCompuesta? = null

    private fun componerValidacion(): ValidacionCompuesta {
        validacionCompuesta = ValidacionCompuesta()
            .add(validacionNombre)
            .add(validacionPorcentaje)
        return validacionCompuesta!!
    }

    override val hayError: Boolean
        get() = validacionCompuesta?.hayError ?: componerValidacion().hayError
    override val mensajeError: String?
        //        get() = "Revisa los datos del contacto"
        get() = validacionCompuesta?.mensajeError ?: componerValidacion().mensajeError
}