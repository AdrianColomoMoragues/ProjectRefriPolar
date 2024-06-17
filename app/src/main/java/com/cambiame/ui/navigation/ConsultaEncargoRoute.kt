package com.cambiame.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cambiame.ui.features.consultaencargo.EncargoConsultaScreen
import com.cambiame.ui.features.consultaencargo.EncargoConsultaViewModel

const val ConsultaEncargoGraphRoute = "consulta_encargos"
const val ConsultaEncargoParameterName = "idEncargo"

fun NavController.navigateToConsultaEncargo(
    idEncargo: Int,
    navOptions: NavOptions? = null,
) {
    this.navigate("$ConsultaEncargoGraphRoute/$idEncargo", navOptions)
}

fun NavGraphBuilder.consultaEncargoScreen(
    vm: EncargoConsultaViewModel,
) {
    composable(
        route = "$ConsultaEncargoGraphRoute/{$ConsultaEncargoParameterName}",
        arguments = listOf(
            navArgument(ConsultaEncargoParameterName) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val idEncargo: Int? = backStackEntry.arguments?.getInt(ConsultaEncargoParameterName, -1)
        vm.setEncargoState(idEncargo!!)
        EncargoConsultaScreen(encargoState = vm.encargoState)
    }
}