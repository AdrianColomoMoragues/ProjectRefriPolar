package com.cambiame.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cambiame.ui.features.formempleado.EmpleadoFormScreen
import com.cambiame.ui.features.formempleado.EmpleadoFormViewModel

const val FormEmpleadoGraphRoute = "form_empleado"
const val FormEmpleadoParameterName = "idEmpleado"

fun NavController.navigateToEditarEmpleado(
    idEmpleado: Int,
    navOptions: NavOptions? = null,
) {
    this.navigate("$FormEmpleadoGraphRoute/$idEmpleado", navOptions)
}

fun NavController.navigateToCrearEmpleado(navOptions: NavOptions? = null) {
    this.navigate("$FormEmpleadoGraphRoute/-1", navOptions)
}

fun NavGraphBuilder.formEmpleadoScreen(
    vm: EmpleadoFormViewModel,
    onNavigateTrasFormEmpleado: (actualizaEmpleado: Boolean) -> Unit
) {
    composable(
        route = "$FormEmpleadoGraphRoute/{$FormEmpleadoParameterName}",
        arguments = listOf(
            navArgument(FormEmpleadoParameterName) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val idEmpleado: Int? = backStackEntry.arguments?.getInt(FormEmpleadoParameterName, -1)
        if (idEmpleado != null
            && idEmpleado != -1
            && vm.empleadoState.id != idEmpleado
        ) {
            vm.setEmpleadoState(idEmpleado!!)
        }
        EmpleadoFormScreen(
            editando = vm.editandoEmpleadoExistenteState,
            empleadoState = vm.empleadoState,
            listaCategorias = vm.listaCategorias,
            validacionEmpleadoUiState = vm.validacionEmpleadoState,
            onEmpleadoFormEvent = vm::OnEmpleadoEvent,
            onNavigateTrasFormEmpleado = onNavigateTrasFormEmpleado
        )
    }
}