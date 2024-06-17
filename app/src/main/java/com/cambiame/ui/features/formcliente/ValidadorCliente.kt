package com.cambiame.ui.features.formcliente

import com.cambiame.ui.features.ClienteUiState
import com.pmdm.agenda.utilities.validacion.Validador
import com.pmdm.agenda.utilities.validacion.ValidadorCompuesto
import com.pmdm.agenda.utilities.validacion.validadores.ValidadorCorreo
import com.pmdm.agenda.utilities.validacion.validadores.ValidadorLongitudMaximaTexto
import com.pmdm.agenda.utilities.validacion.validadores.ValidadorTelefono
import com.pmdm.agenda.utilities.validacion.validadores.ValidadorTextoNoVacio
import javax.inject.Inject

class ValidadorCliente @Inject constructor() : Validador<ClienteUiState> {
    val validadorNombre = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("El nombre no puede estar vacío"))
        .add(ValidadorLongitudMaximaTexto(40))
    val validadorLocalidad = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("La localidad no puede estar vacío"))
        .add(ValidadorLongitudMaximaTexto(40))
    val validadorCaracteristicas = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("Las caracteristicas no puede estar vacías"))
        .add(ValidadorLongitudMaximaTexto(50))
    val validadorDireccion = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("La direccion no puede estar vacia"))
        .add(ValidadorLongitudMaximaTexto(60))
    val validadorCorreo = ValidadorCorreo()
    val validadorTelefono = ValidadorTelefono()

    override fun valida(clienteState : ClienteUiState): ValidacionClienteUiState {
        val validacionNombre = validadorNombre.valida(clienteState.nombre)
        val validacionLocalidad = validadorLocalidad.valida(clienteState.localidad)
        val validacionCaracteristicas = validadorCaracteristicas.valida(clienteState.caracteristicas)
        val validacionDireccion = validadorDireccion.valida(clienteState.direccion)
        val validacionCorreo = validadorCorreo.valida(clienteState.correo)
        val validacionTelefono = validadorTelefono.valida(clienteState.telefono)

        return ValidacionClienteUiState(
            validacionNombre = validacionNombre,
            validacionLocalidad = validacionLocalidad,
            validacionCaracteristicas = validacionCaracteristicas,
            validacionDireccion = validacionDireccion,
            validacionCorreo = validacionCorreo,
            validacionTelefono = validacionTelefono
        )
    }
}