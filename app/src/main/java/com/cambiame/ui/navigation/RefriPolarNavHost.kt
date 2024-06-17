package com.cambiame.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.cambiame.ui.features.consultacliente.ClienteConsultaViewModel
import com.cambiame.ui.features.consultaempleado.EmpleadoConsultaViewModel
import com.cambiame.ui.features.consultaencargo.EncargoConsultaViewModel
import com.cambiame.ui.features.formcliente.ClienteFormViewModel
import com.cambiame.ui.features.formempleado.EmpleadoFormViewModel
import com.cambiame.ui.features.formencargo.EncargoFormViewModel
import com.cambiame.ui.features.verclientes.ListaClientesViewModel
import com.cambiame.ui.features.verempleados.ListaEmpleadosViewModel
import com.cambiame.ui.features.verencargos.ListaEncargosViewModel

@Composable
fun RefriPolarNavHost(
    navController: NavHostController,
    mostrarMenu: MutableState<Boolean>,
    vmListaClientes: ListaClientesViewModel,
    vmFormClientes: ClienteFormViewModel,
    vmConsultaCliente: ClienteConsultaViewModel,
    vmListaEmpleados: ListaEmpleadosViewModel,
    vmFormEmpleados: EmpleadoFormViewModel,
    vmConsultaEmpleados: EmpleadoConsultaViewModel,
    vmListaEncargos: ListaEncargosViewModel,
    vmFormEncargo: EncargoFormViewModel,
    vmConsultaEncargo: EncargoConsultaViewModel,
) {

    NavHost(
        navController = navController,
        startDestination = ListaEncargosGraphRoute
    ) {
        listaEncargosScreen(
            vm = vmListaEncargos,
            onNavigateCrearEncargo = {
                vmFormEncargo.cargarDatos()
                vmFormEncargo.clearEncargoState()
                navController.navigateToCrearEncargo()
            },
            onNavigateConsultaEncargo = { idEncargo ->
                navController.navigateToConsultaEncargo(idEncargo)
            },
            mostrarMenu = mostrarMenu
        )
        listaClientesScreen(
            vm = vmListaClientes,
            onNavigateCrearCliente = {
                vmFormClientes.clearClienteState()
                navController.navigateToCrearCliente()
            },
            onNavigateConsultaCliente = { idCliente ->
                vmFormClientes.clearClienteState()
                navController.navigateToConsultaCliente(idCliente)
            },
            mostrarMenu = mostrarMenu
        )
        listaEmpleadosScreen(
            vm = vmListaEmpleados,
            onNavigateCrearEmpleados = {
                vmFormEmpleados.cargarListaCategorias()
                vmFormEmpleados.clearEmpleadoState()
                navController.navigateToCrearEmpleado()
            },
            onNavigateConsultaEmpleado = { idEmpleado ->
                navController.navigateToConsultaEmpleado(idEmpleado)
            },
            mostrarMenu = mostrarMenu
        )
        formEmpleadoScreen(
            vm = vmFormEmpleados,
            onNavigateTrasFormEmpleado = { actualizaEmpleado ->
                navController.popBackStack()
                if (actualizaEmpleado) {
                    vmListaEmpleados.cargaEmpleados()
                }
            }
        )
        formEncargoScreen(
            vm = vmFormEncargo,
            onNavigateTrasFormEncargo = { actualizaEncargo ->
                navController.popBackStack()
                if (actualizaEncargo) {
                    vmListaEncargos.cargaEmpleados()
                }
            }
        )
        formClienteScreen(
            vm = vmFormClientes,
            onNavigateTrasFormCliente = { actualizaCliente ->
                navController.popBackStack()
                if (actualizaCliente) {
                    vmListaClientes.cargaEmpleados()
                }
            }
        )
        consultaClienteScreen(
            vm = vmConsultaCliente
        )
        consultaEmpleadoScreen(
            vm = vmConsultaEmpleados
        )
        consultaEncargoScreen(
            vm = vmConsultaEncargo
        )
    }
}