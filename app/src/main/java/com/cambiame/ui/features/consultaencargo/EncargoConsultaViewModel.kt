package com.cambiame.ui.features.consultaencargo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cambiame.data.ClientesRepository
import com.cambiame.data.EmpleadosRepository
import com.cambiame.data.EncargosRepository
import com.cambiame.models.Encargo
import com.cambiame.ui.features.EncargoUiState
import com.cambiame.ui.features.toEncargoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EncargoConsultaViewModel @Inject constructor(
    private val encargosRepository: EncargosRepository,
    private val empleadosRepository: EmpleadosRepository,
    private val clientesRepository: ClientesRepository,
) : ViewModel() {
    class EncargoFormViewModelException(message: String) : Exception(message)

    var encargoState by mutableStateOf(EncargoUiState())

    fun setEncargoState(idEncargo: Int) {
        viewModelScope.launch {
            val e: Encargo = encargosRepository.get(idEncargo)
                ?: throw EncargoFormViewModelException("El id $idEncargo no existe en la base de datos")
            encargoState = e.toEncargoUiState()
        }
    }
}