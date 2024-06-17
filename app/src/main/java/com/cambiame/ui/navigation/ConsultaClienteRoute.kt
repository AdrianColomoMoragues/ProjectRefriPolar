package com.cambiame.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cambiame.ui.features.consultacliente.ClienteConsultaScreen
import com.cambiame.ui.features.consultacliente.ClienteConsultaViewModel

const val ConsultaClienteGraphRoute = "consulta_cliente"
const val ConsultaClienteParameteName = "idCliente"

fun NavController.navigateToConsultaCliente(
    idCliente: Int,
    navOptions: NavOptions? = null,
) {
    this.navigate("$ConsultaClienteGraphRoute/$idCliente", navOptions)
}

fun NavGraphBuilder.consultaClienteScreen(
    vm: ClienteConsultaViewModel,
) {
    composable(
        route = "$ConsultaClienteGraphRoute/{$ConsultaClienteParameteName}",
        arguments = listOf(
            navArgument(ConsultaClienteParameteName) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val idCliente: Int? = backStackEntry.arguments?.getInt(ConsultaClienteParameteName, -1)
        vm.setClienteState(idCliente!!)
        ClienteConsultaScreen(clienteState = vm.clienteState)
    }
}