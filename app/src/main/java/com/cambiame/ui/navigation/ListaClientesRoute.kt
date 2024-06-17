package com.cambiame.ui.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cambiame.ui.features.verclientes.ListaClientesEvents
import com.cambiame.ui.features.verclientes.ListaClientesScreen
import com.cambiame.ui.features.verclientes.ListaClientesViewModel

const val ListaClienteGraphController = "lista_clientes"

fun NavController.navigateToListaClientes(navOptions: NavOptions? = null) {
    this.navigate(ListaClienteGraphController, navOptions)
}

fun NavGraphBuilder.listaClientesScreen(
    vm: ListaClientesViewModel,
    onNavigateCrearCliente: () -> Unit,
    onNavigateConsultaCliente: (idCliente: Int) -> Unit,
    mostrarMenu: MutableState<Boolean>
) {
    composable(
        route = ListaClienteGraphController
    ) {
        ListaClientesScreen(
            clientesState = vm.clientesState,
            clienteSeleccionadoState = vm.clienteSeleccionadoState,
            onAddClicked = {
                vm.onListaClienteEvent(ListaClientesEvents.OnCrearCliente(onNavigateCrearCliente))
            },
            onCosultaClicked = {
                vm.onListaClienteEvent(
                    ListaClientesEvents.OnConsultaCliente(
                        onNavigateConsultaCliente
                    )
                )
            },
            onContactoClicked = { e ->
                vm.onListaClienteEvent(ListaClientesEvents.OnClickCliente(e))
            },
            mostrarMenu = mostrarMenu
        )
    }
}