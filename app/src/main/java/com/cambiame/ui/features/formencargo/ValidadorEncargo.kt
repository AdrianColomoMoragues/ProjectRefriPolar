package com.cambiame.ui.features.formencargo

import android.util.Range
import com.cambiame.ui.features.EncargoUiState
import com.pmdm.agenda.utilities.validacion.Validador
import com.pmdm.agenda.utilities.validacion.ValidadorCompuesto
import com.pmdm.agenda.utilities.validacion.validadores.ValidadorLongitudMaximaTexto
import com.pmdm.agenda.utilities.validacion.validadores.ValidadorNumeroEntero
import com.pmdm.agenda.utilities.validacion.validadores.ValidadorTextoNoVacio
import javax.inject.Inject

class ValidadorEncargo @Inject constructor() : Validador<EncargoUiState> {
    val validadorNombre = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("El nombre puede estar vacío"))
        .add(ValidadorLongitudMaximaTexto(40))
    val validadorPorcentaje = ValidadorCompuesto<String>()
        .add(ValidadorNumeroEntero(Range(0,100), "El valor tiene que ser un numero entre 0 a 100"))

    override fun valida(datos: EncargoUiState): ValidacionEncargoUiState {
        val validacionNombre = validadorNombre.valida(datos.nombre)
        val validacionPorcentaje = validadorPorcentaje.valida(datos.porcentaje)

        return  ValidacionEncargoUiState(
            validacionNombre = validacionNombre,
            validacionPorcentaje = validacionPorcentaje
        )
    }
}