package com.cambiame.ui.features.consultaempleado

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cambiame.data.EmpleadosRepository
import com.cambiame.models.Empleado
import com.cambiame.ui.features.EmpleadoUiState
import com.cambiame.ui.features.toEmpleadoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmpleadoConsultaViewModel @Inject constructor(
    private val empleadosRepository: EmpleadosRepository,
) : ViewModel() {
    class EmpleadoFormViewModelException(message: String) : Exception(message)

    var empleadoState by mutableStateOf(EmpleadoUiState())
        private set

    fun setEmpleadoState(idEmpleado: Int) {
        viewModelScope.launch {
            val e: Empleado = empleadosRepository.get(idEmpleado)
                ?: throw EmpleadoFormViewModelException("El id $idEmpleado no existe en la base de datos")
            empleadoState = e.toEmpleadoUiState()
        }
    }
}