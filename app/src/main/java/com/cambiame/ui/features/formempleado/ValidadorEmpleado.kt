package com.cambiame.ui.features.formempleado

import android.util.Range
import com.cambiame.ui.features.EmpleadoUiState
import com.cambiame.utilities.validacion.validadores.ValidadorCodigoPostal
import com.cambiame.utilities.validacion.validadores.ValidadorSalario
import com.pmdm.agenda.utilities.validacion.Validador
import com.pmdm.agenda.utilities.validacion.ValidadorCompuesto
import com.pmdm.agenda.utilities.validacion.validadores.ValidadorCorreo
import com.pmdm.agenda.utilities.validacion.validadores.ValidadorLongitudMaximaTexto
import com.pmdm.agenda.utilities.validacion.validadores.ValidadorNumeroEntero
import com.pmdm.agenda.utilities.validacion.validadores.ValidadorTelefono
import com.pmdm.agenda.utilities.validacion.validadores.ValidadorTextoNoVacio
import javax.inject.Inject

class ValidadorEmpleado @Inject constructor() : Validador<EmpleadoUiState> {
    val validadorNombre = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("El nombre puede estar vacío"))
        .add(ValidadorLongitudMaximaTexto(20))
    val validadorApellidos = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("Los apellidos pueden estar vacíos"))
        .add(ValidadorLongitudMaximaTexto(50))
    val validadorAntiguedad = ValidadorNumeroEntero(Range(0, 20), "Tiene que ser un numero entero")
    val validadorDireccion = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("La direccion no puede estar vacia"))
        .add(ValidadorLongitudMaximaTexto(60))
    val validadorCorreo = ValidadorCorreo()
    val validadorTelefono = ValidadorTelefono()
    val validadorCodigoPostal = ValidadorCodigoPostal()
    val validadorCiudad = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("La ciudad no puede estar vacía"))
        .add(ValidadorLongitudMaximaTexto(40))
    val validadorProvincia = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("La provincia no puede estar vacía"))
        .add(ValidadorLongitudMaximaTexto(40))
    val validadorSalario = ValidadorSalario()
    override fun valida(datos: EmpleadoUiState): ValidacionEmpleadoUiState {
        val validacionNombre = validadorNombre.valida(datos.nombre)
        val validacionApellidos = validadorApellidos.valida(datos.apellidos)
        val validacionAntiguedad = validadorAntiguedad.valida(datos.antiguedad.toString())
        val validacionCorreo = validadorCorreo.valida(datos.mail)
        val validacionDireccion = validadorDireccion.valida(datos.direccion)
        val validacionTelefono = validadorTelefono.valida(datos.telefono)
        val validacionCodigoPostal = validadorCodigoPostal.valida(datos.cp)
        val validacionCiudad = validadorCiudad.valida(datos.ciudad)
        val validacionProvincia = validadorProvincia.valida(datos.provincia)
        val validacionSalario = validadorSalario.valida(datos.salario)

        return ValidacionEmpleadoUiState(
            validacionNombre = validacionNombre,
            validacionApellidos = validacionApellidos,
            validacionAntiguedad = validacionAntiguedad,
            validacionCorreo = validacionCorreo,
            validacionDireccion = validacionDireccion,
            validacionTelefono = validacionTelefono,
            validacionCodigoPostal = validacionCodigoPostal,
            validacionCiudad = validacionCiudad,
            validacionProvincia = validacionProvincia,
            validacionSalario = validacionSalario
        )
    }
}

