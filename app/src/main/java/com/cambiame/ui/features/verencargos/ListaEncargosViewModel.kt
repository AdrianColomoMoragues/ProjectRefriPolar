package com.cambiame.ui.features.verencargos

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cambiame.data.EncargosRepository
import com.cambiame.models.Encargo
import com.cambiame.ui.features.EncargoUiState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListaEncargosViewModel @Inject constructor(
    private val encargosRepository: EncargosRepository
): ViewModel() {
    var encargosState by mutableStateOf(listOf<Encargo>())

    var encargoSeleccionadoState: EncargoUiState? by mutableStateOf(EncargoUiState())

    private suspend fun getEncargos(): List<Encargo> = encargosRepository.get().toList()

    fun deseleccionaEncargo() {
        encargoSeleccionadoState = null
    }

    fun cargaEmpleados() {
        deseleccionaEncargo()
        viewModelScope.launch {
            try {
                encargosState = getEncargos()
            }catch (e: Exception) {
                Log.d("ListaEmpleadosViewModel", "Cargando Empleados: ${e.localizedMessage}")
                encargosState = listOf()
            }
        }
    }

    fun onListaEncargosEvent(e: ListaEncargosEvents) {
        when (e){
            is ListaEncargosEvents.OnClickEncargo -> {
                encargoSeleccionadoState =
                    if (encargoSeleccionadoState?.id != e.encargo.id) e.encargo else null
            }
            is ListaEncargosEvents.OnConsultaEncargo -> {
                e.onNavigateConsultaEncargo(encargoSeleccionadoState!!.id)
                deseleccionaEncargo()
            }
            is ListaEncargosEvents.OnCrearEncargo -> {
                e.onNavigateCrearEncargo()
            }
            is ListaEncargosEvents.OnDeleteEncargo -> {
                viewModelScope.launch {
                    try {
                        encargosRepository.delete(encargoSeleccionadoState!!.id)
                        cargaEmpleados()
                    } catch (e: Exception) {
                        Log.d("ListaContactosViewModel", "Borrando Contacto: ${e.localizedMessage}")
                    }
                }
            }
            is ListaEncargosEvents.OnEditEncargo -> {
                e.onNavigateEditEncargo(encargoSeleccionadoState!!.id)
                deseleccionaEncargo()
            }
        }
    }

    init { cargaEmpleados() }
}