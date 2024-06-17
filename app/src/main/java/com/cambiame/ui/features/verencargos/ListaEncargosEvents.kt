package com.cambiame.ui.features.verencargos

import com.cambiame.ui.features.EncargoUiState

sealed interface ListaEncargosEvents {
    data class OnClickEncargo(val encargo: EncargoUiState) : ListaEncargosEvents
    data class OnConsultaEncargo(val onNavigateConsultaEncargo: (idEncargo: Int) -> Unit) :
        ListaEncargosEvents

    data class OnEditEncargo(val onNavigateEditEncargo: (idEncargo: Int) -> Unit) :
        ListaEncargosEvents

    data class OnCrearEncargo(val onNavigateCrearEncargo: () -> Unit) : ListaEncargosEvents
    data object OnDeleteEncargo : ListaEncargosEvents
}
