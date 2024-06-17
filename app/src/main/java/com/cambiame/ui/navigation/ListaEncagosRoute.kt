package com.cambiame.ui.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.cambiame.ui.features.verencargos.ListaEncargosEvents
import com.cambiame.ui.features.verencargos.ListaEncargosScreen
import com.cambiame.ui.features.verencargos.ListaEncargosViewModel

const val ListaEncargosGraphRoute = "lista_encargos"

fun NavController.navigateToListaEncargos(navOptions: NavOptions? = null) {
    this.navigate(ListaEncargosGraphRoute, navOptions)
}

fun NavGraphBuilder.listaEncargosScreen(
    vm: ListaEncargosViewModel,
    onNavigateCrearEncargo: () -> Unit,
    onNavigateConsultaEncargo: (idEncargo: Int) -> Unit,
    mostrarMenu: MutableState<Boolean>
) {
    composable(
        route = ListaEncargosGraphRoute
    ) {
        ListaEncargosScreen(
            encargosState = vm.encargosState,
            encargoSeleccionadoState = vm.encargoSeleccionadoState,
            onAddClicked = {
                vm.onListaEncargosEvent(ListaEncargosEvents.OnCrearEncargo(onNavigateCrearEncargo))
            },
            onContactoClicked = { e ->
                vm.onListaEncargosEvent(ListaEncargosEvents.OnClickEncargo(e))
            },
            onCosultaClicked = {
                vm.onListaEncargosEvent(
                    ListaEncargosEvents.OnConsultaEncargo(
                        onNavigateConsultaEncargo
                    )
                )
            },
            mostrarMenu = mostrarMenu
        )
    }
}