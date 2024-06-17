package com.cambiame.ui.features.formencargo

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cambiame.data.ClientesRepository
import com.cambiame.data.EmpleadosRepository
import com.cambiame.data.EncargosRepository
import com.cambiame.models.Clientes
import com.cambiame.models.Empleado
import com.cambiame.models.Encargo
import com.cambiame.ui.features.EmpleadoUiState
import com.cambiame.ui.features.EncargoUiState
import com.cambiame.ui.features.toEmpleadoUiState
import com.cambiame.ui.features.toEncargo
import com.cambiame.ui.features.toEncargoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class EncargoFormViewModel @Inject constructor(
    private val encargosRepository: EncargosRepository,
    private val clientesRepository: ClientesRepository,
    private val empleadosRepository: EmpleadosRepository,
    private val validacionEncargo: ValidadorEncargo
) : ViewModel() {
    class EncargoFormViewModelException(message: String) : Exception(message)

    var listaClientes by mutableStateOf(listOf<Clientes>())
    var listaEmpleado by mutableStateOf(listOf<Empleado>())


    var editandoEncargoExistenteState: Boolean = false
        private set

    var encargoState by mutableStateOf(EncargoUiState())
        private set

    var empleadoSeleccionado: EmpleadoUiState? by mutableStateOf(EmpleadoUiState())

    var validacionEncargoState by mutableStateOf(ValidacionEncargoUiState())
        private set

    private suspend fun getEmpleados(): List<Empleado> = empleadosRepository.get().toList()

    private suspend fun getClientes(): List<Clientes> = clientesRepository.get().toList()


    fun setEncargoState(idEncargo: Int) {
        viewModelScope.launch {
            editandoEncargoExistenteState = true
            val e: Encargo = encargosRepository.get(idEncargo)
                ?: throw EncargoFormViewModelException("El id $idEncargo no existe en la base de datos")
            encargoState = e.toEncargoUiState()
        }
    }

    fun cargarDatos(){
        viewModelScope.launch {
            try {
                listaEmpleado = getEmpleados()
                listaClientes = getClientes()
            } catch (e: Exception) {
                Log.d("EncargoFormViewModel", "Cargando Empleados: ${e.localizedMessage}")
                listaEmpleado = listOf()
                listaClientes = listOf()
            }
        }
    }

    fun clearEncargoState() {
        editandoEncargoExistenteState = false
        encargoState = EncargoUiState()
        empleadoSeleccionado = null
    }

    init { cargarDatos() }

    fun OnEncargoEvent(e: EncargoFormEvent) {
        when (e) {
            is EncargoFormEvent.OnSelectEmpleado -> {
                empleadoSeleccionado =
                    if(empleadoSeleccionado?.id != e.empleado.id) e.empleado.toEmpleadoUiState() else null
            }
            is EncargoFormEvent.OnChangeCliente -> {
                encargoState = encargoState.copy(idCliente = e.cliente)
            }
            is EncargoFormEvent.OnChangeEncargado -> {
                encargoState = encargoState.copy(idEncargado = e.encargo)
            }
            is EncargoFormEvent.OnChangeNombre -> {
                encargoState = encargoState.copy(nombre = e.nombre)
            }
            is EncargoFormEvent.OnChangeTerminada -> {
                encargoState = encargoState.copy(terminada = e.terminada)
            }
            is EncargoFormEvent.OnChangePrioridad -> {
                encargoState = encargoState.copy(prioridad = e.prioridad)
            }
            is EncargoFormEvent.OnChangePorcentaje -> {
                encargoState = encargoState.copy(porcentaje = e.porcentaje)
            }
            is EncargoFormEvent.OnChangeTipo -> {
                encargoState = encargoState.copy(tipo = e.tipo)
                for (empleado in encargoState.empleadosCollection){
                    if(empleado.codcategoriaProfesional!!.encargo.toLowerCase() != encargoState.tipo)
                    {
                        encargoState = encargoState.copy(empleadosCollection = mutableListOf<Empleado>())
                    }
                }
            }
            is EncargoFormEvent.OnAddEmpleado -> {
                val listaEmpleados = encargoState.empleadosCollection.toMutableList()
                listaEmpleados.add(e.empleado)
                encargoState = encargoState.copy(empleadosCollection = listaEmpleados)
            }
            is EncargoFormEvent.OnDeleteEmpleado -> {
                val listaEmpleado = encargoState.empleadosCollection.toMutableList()
                listaEmpleado.remove(e.empleado)
                encargoState = encargoState.copy(empleadosCollection = listaEmpleado)
                empleadoSeleccionado = null
            }
            is EncargoFormEvent.OnDismissError -> {
                validacionEncargoState = ValidacionEncargoUiState()
            }
            is EncargoFormEvent.OnSaveCliente -> {
                validacionEncargoState = validacionEncargo.valida(encargoState)
                if(!validacionEncargoState.hayError) {
                    val encargo: Encargo = encargoState.toEncargo()
                    runBlocking {
                        if(editandoEncargoExistenteState){
                            encargosRepository.update(encargo)
                        } else {
                            encargosRepository.insert(encargo)
                        }
                    }
                    e.onNavigateTrasFormEncargo(true)
                }
            }
        }
    }
}