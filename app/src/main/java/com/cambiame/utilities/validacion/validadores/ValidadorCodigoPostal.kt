package com.cambiame.utilities.validacion.validadores

import com.pmdm.agenda.utilities.validacion.Validacion
import com.pmdm.agenda.utilities.validacion.Validador

class ValidadorCodigoPostal(
    private val error: String = "Código postal no válido"
) : Validador<String> {
    override fun valida(texto: String): Validacion {
        val regex = Regex("^\\d{5}$") // Expresión regular para validar código postal de 5 dígitos

        return object : Validacion {
            override val hayError: Boolean
                get() = !regex.matches(texto)
            override val mensajeError: String
                get() = error
        }
    }
}