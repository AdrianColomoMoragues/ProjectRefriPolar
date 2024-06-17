package com.cambiame.ui.features.consultacliente

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cambiame.data.ClientesRepository
import com.cambiame.models.Clientes
import com.cambiame.ui.features.ClienteUiState
import com.cambiame.ui.features.formcliente.ClienteFormViewModel
import com.cambiame.ui.features.toClienteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClienteConsultaViewModel @Inject constructor(
    private val clientesRepository: ClientesRepository,
) : ViewModel() {
    class ClienteViewModelException(message: String) : Exception(message)

    var clienteState by mutableStateOf(ClienteUiState())
        private set

    fun setClienteState(idCliente: Int) {
        viewModelScope.launch {
            val c: Clientes = clientesRepository.get(idCliente)
                ?: throw ClienteFormViewModel.ClienteViewModelException("El id $idCliente no existe en la base de datos")
            clienteState = c.toClienteUiState()
        }
    }

}