package com.cambiame.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cambiame.ui.features.consultaempleado.EmpleadoConsultaScreen
import com.cambiame.ui.features.consultaempleado.EmpleadoConsultaViewModel

const val ConsultaEmpleadoGraphRoute = "consulta_empleado"
const val ConsultaEmpleadoParameterName = "idEmpleado"

fun NavController.navigateToConsultaEmpleado(
    idEmpleado: Int,
    navOptions: NavOptions? = null,
) {
    this.navigate("$ConsultaEmpleadoGraphRoute/$idEmpleado", navOptions)
}

fun NavGraphBuilder.consultaEmpleadoScreen(
    vm: EmpleadoConsultaViewModel
) {
    composable(
        route = "$ConsultaEmpleadoGraphRoute/{$ConsultaEmpleadoParameterName}",
        arguments = listOf(
            navArgument(ConsultaEmpleadoParameterName) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val idEmpleado: Int? = backStackEntry.arguments?.getInt(ConsultaEmpleadoParameterName, -1)
        vm.setEmpleadoState(idEmpleado!!)
        EmpleadoConsultaScreen(empleadoState = vm.empleadoState)
    }
}