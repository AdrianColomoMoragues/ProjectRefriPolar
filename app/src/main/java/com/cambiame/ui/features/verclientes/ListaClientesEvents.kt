package com.cambiame.ui.features.verclientes

import com.cambiame.ui.features.ClienteUiState

sealed interface ListaClientesEvents {
    data class OnClickCliente(val cliente: ClienteUiState) : ListaClientesEvents
    data class OnConsultaCliente(val onNavigateConsultaCliente: (idCliente: Int) -> Unit) :
        ListaClientesEvents

    data class OnEditCliente(val onNavigateEditCliente: (idCliente: Int) -> Unit) :
        ListaClientesEvents

    data class OnCrearCliente(val onNavigateCrearCliente: () -> Unit) : ListaClientesEvents
    data object OnDeleteCliente : ListaClientesEvents
}