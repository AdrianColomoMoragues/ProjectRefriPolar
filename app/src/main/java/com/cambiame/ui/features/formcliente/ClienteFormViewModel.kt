package com.cambiame.ui.features.formcliente

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cambiame.data.ClientesRepository
import com.cambiame.models.Clientes
import com.cambiame.ui.features.ClienteUiState
import com.cambiame.ui.features.toCliente
import com.cambiame.ui.features.toClienteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ClienteFormViewModel @Inject constructor(
    private val clientesRepository: ClientesRepository,
    private val validadorCliente: ValidadorCliente,
) : ViewModel() {
    class ClienteViewModelException(message: String) : Exception(message)

    var editandoClienteExistenteState: Boolean = false
        private set

    var clienteState by mutableStateOf(ClienteUiState())
        private set
    var validacionContactoState by mutableStateOf(ValidacionClienteUiState())
        private set

    fun setClienteState(idCliente: Int) {
        viewModelScope.launch {
            editandoClienteExistenteState = true
            val c: Clientes = clientesRepository.get(idCliente)
                ?: throw ClienteViewModelException("El id $idCliente no existe en la base de datos")
            clienteState = c.toClienteUiState()
        }
    }

    fun clearClienteState() {
        editandoClienteExistenteState = false
        clienteState = ClienteUiState()
    }

    fun onClienteEvent(e: ClienteFormEvent) {
        when (e) {
            is ClienteFormEvent.OnChangeCaracteristicas -> {
                clienteState = clienteState.copy(caracteristicas = e.caracteristicas)
                validacionContactoState = validacionContactoState.copy(
                    validacionLocalidad = validadorCliente.validadorCaracteristicas.valida(e.caracteristicas)
                )
            }

            is ClienteFormEvent.OnChangeCorreo -> {
                clienteState = clienteState.copy(correo = e.correo)
                validacionContactoState = validacionContactoState.copy(
                    validacionCorreo = validadorCliente.validadorCorreo.valida(e.correo)
                )
            }

            is ClienteFormEvent.OnChangeDireccion -> {
                clienteState = clienteState.copy(direccion = e.direccion)
                validacionContactoState = validacionContactoState.copy(
                     validacionDireccion = validadorCliente.validadorDireccion.valida(e.direccion)
                )
            }

            is ClienteFormEvent.OnChangeLocalidad -> {
                clienteState = clienteState.copy(localidad = e.localidad)
                validacionContactoState = validacionContactoState.copy(
                    validacionLocalidad = validadorCliente.validadorLocalidad.valida(e.localidad)
                )
            }

            is ClienteFormEvent.OnChangeNombre -> {
                clienteState = clienteState.copy(nombre = e.nombre)
                validacionContactoState = validacionContactoState.copy(
                    validacionLocalidad = validadorCliente.validadorNombre.valida(e.nombre)
                )
            }

            is ClienteFormEvent.OnChangeTelefono -> {
                clienteState = clienteState.copy(telefono = e.telefono)
                validacionContactoState = validacionContactoState.copy(
                    validacionTelefono = validadorCliente.validadorTelefono.valida(e.telefono)
                )
            }

            is ClienteFormEvent.OnDismissError -> {
                validacionContactoState = ValidacionClienteUiState()
            }

            is ClienteFormEvent.OnSaveCliente -> {
                validacionContactoState = validadorCliente.valida(clienteState)
                if(!validacionContactoState.hayError) {
                    val cliente: Clientes = clienteState.toCliente()
                    runBlocking {
                        if (editandoClienteExistenteState) {
                            clientesRepository.update(cliente)
                        } else {
                            clientesRepository.insert(cliente)
                        }
                        clienteState = ClienteUiState()
                    }
                    e.onNavigateTrasFormCliente(true)
                }
            }
        }
    }
}