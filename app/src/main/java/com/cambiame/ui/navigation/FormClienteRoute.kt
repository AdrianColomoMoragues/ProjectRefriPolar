package com.cambiame.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cambiame.ui.features.formcliente.ClienteFormScreen
import com.cambiame.ui.features.formcliente.ClienteFormViewModel

const val FormClienteGraphRoute = "form_cliente"
const val FormClienteParameterName = "idCliente"

fun NavController.navigateToEditarCliente(
    idCliente: Int,
    navOptions: NavOptions? = null,
) {
    this.navigate("$FormClienteGraphRoute/$idCliente", navOptions)
}

fun NavController.navigateToCrearCliente(navOptions: NavOptions? = null) {
    this.navigate("$FormClienteGraphRoute/-1", navOptions)
}

fun NavGraphBuilder.formClienteScreen(
    vm: ClienteFormViewModel,
    onNavigateTrasFormCliente: (actualizaCliente: Boolean) -> Unit
) {
    composable(
        route = "$FormClienteGraphRoute/{$FormClienteParameterName}",
        arguments = listOf(
            navArgument(FormClienteParameterName) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val idCliente: Int? = backStackEntry.arguments?.getInt(FormClienteParameterName, -1)
        if (idCliente != null
            && idCliente != -1
            && vm.clienteState.id != idCliente
        ) {
            vm.setClienteState(idCliente!!)
        }
        ClienteFormScreen(
            editando = vm.editandoClienteExistenteState,
            clienteState = vm.clienteState,
            validacionContactoState = vm.validacionContactoState,
            onClienteEvent = vm::onClienteEvent,
            onNavigateTrasFormCliente = onNavigateTrasFormCliente
        )
    }
}