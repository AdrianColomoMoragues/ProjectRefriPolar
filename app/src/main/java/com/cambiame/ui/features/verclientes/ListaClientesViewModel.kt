package com.cambiame.ui.features.verclientes

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cambiame.data.ClientesRepository
import com.cambiame.models.Clientes
import com.cambiame.ui.features.ClienteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaClientesViewModel @Inject constructor(
    private val clientesRepository: ClientesRepository,
) : ViewModel() {
    var clienteSeleccionadoState: ClienteUiState? by mutableStateOf(ClienteUiState())

    var clientesState by mutableStateOf(listOf<Clientes>())

    private suspend fun getClientes(): List<Clientes> = clientesRepository.get().toList()

    fun cargaEmpleados() {
        deseleccionaCliente()
        viewModelScope.launch {
            try {
                clientesState = getClientes()
            } catch (e: Exception) {
                Log.d("ListaEmpleadosViewModel", "Cargando Empleados: ${e.localizedMessage}")
                clientesState = listOf()
            }
        }
    }

    fun deseleccionaCliente(){
        clienteSeleccionadoState = null
    }

    fun onListaClienteEvent(e: ListaClientesEvents) {
        when (e) {
            is ListaClientesEvents.OnClickCliente -> {
                clienteSeleccionadoState =
                    if(clienteSeleccionadoState?.id != e.cliente.id) e.cliente else null
            }

            is ListaClientesEvents.OnConsultaCliente -> {
                e.onNavigateConsultaCliente(clienteSeleccionadoState!!.id)
                deseleccionaCliente()
            }

            is ListaClientesEvents.OnCrearCliente -> {
                e.onNavigateCrearCliente()
            }

            is ListaClientesEvents.OnDeleteCliente -> {
                viewModelScope.launch {
                    try {
                        clientesRepository.delete(clienteSeleccionadoState!!.id)
                        cargaEmpleados()
                    } catch (e: Exception) {
                        Log.d("ListaContactosViewModel", "Borrando Contacto: ${e.localizedMessage}")
                    }
                }
            }

            is ListaClientesEvents.OnEditCliente -> {
                e.onNavigateEditCliente(clienteSeleccionadoState!!.id)
                deseleccionaCliente()
            }
        }
    }

    init {
        cargaEmpleados()
    }
}