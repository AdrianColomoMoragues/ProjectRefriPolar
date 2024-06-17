package com.cambiame.ui.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cambiame.ui.features.verempleados.ListaEmpleadosEvents
import com.cambiame.ui.features.verempleados.ListaEmpleadosScreen
import com.cambiame.ui.features.verempleados.ListaEmpleadosViewModel

const val ListaEmpleadosGraphRoute = "lista_empleados"

fun NavController.navigateToListaEmpleados(navOptions: NavOptions? = null) {
    this.navigate(ListaEmpleadosGraphRoute, navOptions)
}

fun NavGraphBuilder.listaEmpleadosScreen(
    vm: ListaEmpleadosViewModel,
    onNavigateCrearEmpleados: () -> Unit,
    onNavigateConsultaEmpleado: (idCliente: Int) -> Unit,
    mostrarMenu: MutableState<Boolean>
) {
    composable(
        route = ListaEmpleadosGraphRoute
    ) {
        ListaEmpleadosScreen(
            empleadosState = vm.empleadosState,
            empleadoSeleccionadoState = vm.empleadoSeleccionado,
            onAddClicked = {
                vm.onListaEmpleados(ListaEmpleadosEvents.OnCrearEmpleado(onNavigateCrearEmpleados))
            },
            onCosultaClicked = {
                vm.onListaEmpleados(
                    ListaEmpleadosEvents.OnConsultaEmpleado(
                        onNavigateConsultaEmpleado
                    )
                )
            },
            onEmpleadoClicked = { e ->
                vm.onListaEmpleados(ListaEmpleadosEvents.OnClickEmpleado(e))
            },
            mostrarMenu = mostrarMenu
        )
    }
}