package com.cambiame.ui.features.formempleado

import com.cambiame.models.CategoriaProfesional

sealed interface EmpleadoFormEvent {
    data class OnChangeNombre(val nombre: String) : EmpleadoFormEvent
    data class OnChangeApellidos(val apellidos: String) : EmpleadoFormEvent
    data class OnChangeCategoriaProfesional(val codCategoria: CategoriaProfesional) : EmpleadoFormEvent
    data class OnChangeAntigueda(val antiguedad: String) : EmpleadoFormEvent
    data class OnChangeSalario(val salario: String) : EmpleadoFormEvent
    data class OnChangeCiudad(val ciudad: String) : EmpleadoFormEvent
    data class OnChangeProvincia(val provincia: String) : EmpleadoFormEvent
    data class OnChangeDireccion(val direccion: String) : EmpleadoFormEvent
    data class OnChangeTelefono(val telefono: String) : EmpleadoFormEvent
    data class OnChangeMail(val mail: String) : EmpleadoFormEvent
    data class OnChangeCodigoPostal(val cp: String): EmpleadoFormEvent
    data class OnChangeReconocimientoMedico(val reconocimientoMiento: Boolean) : EmpleadoFormEvent
    data class OnSaveEmpleado(val onNavigateTrasFormEmpleado: (actualizaEmpleado : Boolean) -> Unit) : EmpleadoFormEvent
    data object OnDismissError : EmpleadoFormEvent
}