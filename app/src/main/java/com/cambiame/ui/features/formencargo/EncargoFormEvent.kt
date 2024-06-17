package com.cambiame.ui.features.formencargo

import com.cambiame.models.Clientes
import com.cambiame.models.Empleado

sealed interface EncargoFormEvent {
    data class OnSelectEmpleado(val empleado: Empleado) : EncargoFormEvent
    data class OnChangeNombre(val nombre:String) : EncargoFormEvent
    data class OnChangeTerminada(val terminada: Boolean) : EncargoFormEvent
    data class OnChangeCliente(val cliente: Clientes): EncargoFormEvent
    data class OnChangeEncargado(val encargo: Empleado) : EncargoFormEvent
    data class OnChangePrioridad(val prioridad: Int) : EncargoFormEvent
    data class OnChangePorcentaje(val porcentaje: String) : EncargoFormEvent
    data class OnChangeTipo(val tipo: String) : EncargoFormEvent
    data class OnAddEmpleado(val empleado: Empleado) : EncargoFormEvent
    data class OnDeleteEmpleado(val empleado: Empleado) : EncargoFormEvent
    data class OnSaveCliente(val onNavigateTrasFormEncargo: (actualizaEncargo : Boolean) -> Unit) : EncargoFormEvent
    data object OnDismissError : EncargoFormEvent
}