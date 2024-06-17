package com.cambiame.utilities.validacion.validadores

import com.pmdm.agenda.utilities.validacion.Validacion
import com.pmdm.agenda.utilities.validacion.Validador

class ValidadorSalario(
    private val error: String = "Salario no válido"
) : Validador<String> {
    override fun valida(valor: String): Validacion {
        val regex = Regex("^\\d+(\\.\\d+)?$")

        return object : Validacion {
            override val hayError: Boolean
                get() = !regex.matches(valor)
            override val mensajeError: String
                get() = error
        }
    }
}