package com.cambiame.ui.features.verempleados

import com.cambiame.ui.features.EmpleadoUiState


sealed interface ListaEmpleadosEvents {
    data class OnClickEmpleado(val empleado: EmpleadoUiState) : ListaEmpleadosEvents
    data class OnConsultaEmpleado(val onNavigateConsultaEmpleado: (idEmpleado: Int) -> Unit) :
        ListaEmpleadosEvents

    data class OnEditEmpleado(val onNavigateEditEmpleado: (idEmpleado: Int) -> Unit) :
        ListaEmpleadosEvents

    data class OnCrearEmpleado(val onNavigateCrearEmpleado: () -> Unit) : ListaEmpleadosEvents
    data object OnDeleteEmpleado : ListaEmpleadosEvents
}