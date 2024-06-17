package com.cambiame.ui.features.verempleados

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cambiame.data.EmpleadosRepository
import com.cambiame.models.Empleado
import com.cambiame.ui.features.EmpleadoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaEmpleadosViewModel @Inject constructor(
    private val empleadosRepository: EmpleadosRepository,
) : ViewModel() {
    var empleadoSeleccionado: EmpleadoUiState? by mutableStateOf(EmpleadoUiState())

    var empleadosState by mutableStateOf(listOf<Empleado>())
        private set

    private suspend fun getEmpleados(): List<Empleado> = empleadosRepository.get().toList()

    fun cargaEmpleados() {
        deseleccionarEmpleado()
        viewModelScope.launch {
            try {
                empleadosState = getEmpleados()
            } catch (e: Exception) {
                Log.d("ListaEmpleadosViewModel", "Cargando Empleados: ${e.localizedMessage}")
                empleadosState = listOf()
            }
        }
    }

    fun deseleccionarEmpleado(){
        empleadoSeleccionado = null
    }

    fun onListaEmpleados(e: ListaEmpleadosEvents) {
        when (e) {
            is ListaEmpleadosEvents.OnClickEmpleado -> {
                empleadoSeleccionado =
                    if(empleadoSeleccionado?.id != e.empleado.id) e.empleado else null
            }
            is ListaEmpleadosEvents.OnConsultaEmpleado -> {
                e.onNavigateConsultaEmpleado(empleadoSeleccionado!!.id)
                deseleccionarEmpleado()
            }
            is ListaEmpleadosEvents.OnCrearEmpleado -> {
                e.onNavigateCrearEmpleado()
            }
            ListaEmpleadosEvents.OnDeleteEmpleado -> {
                viewModelScope.launch {
                    try {
                        empleadosRepository.delete(empleadoSeleccionado!!.id)
                        cargaEmpleados()
                    } catch (e: Exception) {
                        Log.d("ListaContactosViewModel", "Borrando Contacto: ${e.localizedMessage}")
                    }
                }
            }
            is ListaEmpleadosEvents.OnEditEmpleado -> {
                e.onNavigateEditEmpleado(empleadoSeleccionado!!.id)
                deseleccionarEmpleado()
            }
        }
    }

    init {
        cargaEmpleados()
    }
}