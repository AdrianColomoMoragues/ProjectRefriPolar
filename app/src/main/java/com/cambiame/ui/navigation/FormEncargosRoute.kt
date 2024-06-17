package com.cambiame.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cambiame.ui.features.formencargo.EncargoFormScreen
import com.cambiame.ui.features.formencargo.EncargoFormViewModel

const val FormEncargosGraphRoute = "form_encago"
const val FormEncargoParameterName = "idEmpleado"


fun NavController.navigateToEditarEncargo(
    idEncargo: Int,
    navOptions: NavOptions? = null,
) {
    this.navigate("$FormEncargosGraphRoute/$idEncargo", navOptions)
}

fun NavController.navigateToCrearEncargo(navOptions: NavOptions? = null) {
    this.navigate("$FormEncargosGraphRoute/-1", navOptions)
}

fun NavGraphBuilder.formEncargoScreen(
    vm: EncargoFormViewModel,
    onNavigateTrasFormEncargo: (actualizaEncargo: Boolean) -> Unit
) {
    composable(
        route = "$FormEncargosGraphRoute/{$FormEncargoParameterName}",
        arguments = listOf(
            navArgument(FormEncargoParameterName) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val idEncargo: Int? = backStackEntry.arguments?.getInt(FormEncargoParameterName, -1)
        if (idEncargo != null
            && idEncargo != -1
            && vm.encargoState.id != idEncargo
        ) {
            vm.setEncargoState(idEncargo!!)
        }
        EncargoFormScreen(
            empleadoState = vm.empleadoSeleccionado,
            editando = vm.editandoEncargoExistenteState,
            encargoState = vm.encargoState,
            listaClientes = vm.listaClientes,
            listaEmpleados = vm.listaEmpleado,
            validacionContactoState = vm.validacionEncargoState,
            onEncargoEvent = vm::OnEncargoEvent,
            onNavigateTrasFormEncargo = onNavigateTrasFormEncargo
        )
    }
}