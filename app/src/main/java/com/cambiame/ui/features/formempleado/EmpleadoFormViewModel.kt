package com.cambiame.ui.features.formempleado

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cambiame.data.CategoriaProfesionalRepository
import com.cambiame.data.EmpleadosRepository
import com.cambiame.models.CategoriaProfesional
import com.cambiame.models.Empleado
import com.cambiame.ui.features.EmpleadoUiState
import com.cambiame.ui.features.toEmpleado
import com.cambiame.ui.features.toEmpleadoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class EmpleadoFormViewModel @Inject constructor(
    private val empleadosRepository: EmpleadosRepository,
    private val codCategoriaProfesionalRepository: CategoriaProfesionalRepository,
    private val validacionEmpleado: ValidadorEmpleado,
) : ViewModel() {
    class EmpleadoFormViewModelException(message: String) : Exception(message)

    var listaCategorias by mutableStateOf(listOf<CategoriaProfesional>())

    var editandoEmpleadoExistenteState: Boolean = false
        private set

    var empleadoState by mutableStateOf(EmpleadoUiState())
        private set

    var validacionEmpleadoState by mutableStateOf(ValidacionEmpleadoUiState())
        private set

    private suspend fun getCategorias(): List<CategoriaProfesional> =
        codCategoriaProfesionalRepository.get().toList()

    fun setEmpleadoState(idEncargo: Int) {
        viewModelScope.launch {
            editandoEmpleadoExistenteState = true
            val e: Empleado = empleadosRepository.get(idEncargo)
                ?: throw EmpleadoFormViewModelException("El id $idEncargo no existe en la base de datos")
            empleadoState = e.toEmpleadoUiState()
        }
    }

    fun cargarListaCategorias() {
        viewModelScope.launch {
            try {
                listaCategorias = getCategorias()
            } catch (e: Exception) {
                Log.d("EmpleadoForm", "Cargando Empleados: ${e.localizedMessage}")
                listaCategorias = listOf()
            }
        }
    }

    fun clearEmpleadoState() {
        empleadoState = EmpleadoUiState()
        editandoEmpleadoExistenteState = false
    }

    init {
        cargarListaCategorias()
    }

    fun OnEmpleadoEvent(e: EmpleadoFormEvent) {
        when (e) {
            is EmpleadoFormEvent.OnChangeAntigueda -> {
                empleadoState = empleadoState.copy(antiguedad = e.antiguedad)
            }

            is EmpleadoFormEvent.OnChangeApellidos -> {
                empleadoState = empleadoState.copy(apellidos = e.apellidos)
            }

            is EmpleadoFormEvent.OnChangeCategoriaProfesional -> {
                empleadoState = empleadoState.copy(codcategoriaProfesional = e.codCategoria)

            }

            is EmpleadoFormEvent.OnChangeNombre -> {
                empleadoState = empleadoState.copy(nombre = e.nombre)
            }

            is EmpleadoFormEvent.OnChangeReconocimientoMedico -> {
                empleadoState = empleadoState.copy(reconocimientoMedico = e.reconocimientoMiento)
            }

            is EmpleadoFormEvent.OnChangeCiudad -> {
                empleadoState = empleadoState.copy(ciudad = e.ciudad)
            }

            is EmpleadoFormEvent.OnChangeProvincia -> {
                empleadoState = empleadoState.copy(provincia = e.provincia)
            }

            is EmpleadoFormEvent.OnChangeSalario -> {
                empleadoState = empleadoState.copy(salario = e.salario)
            }

            is EmpleadoFormEvent.OnChangeDireccion -> {
                empleadoState = empleadoState.copy(direccion = e.direccion)
            }

            is EmpleadoFormEvent.OnChangeCodigoPostal -> {
                empleadoState = empleadoState.copy(cp = e.cp)
            }

            is EmpleadoFormEvent.OnChangeTelefono -> {
                empleadoState = empleadoState.copy(telefono = e.telefono)
            }

            is EmpleadoFormEvent.OnChangeMail -> {
                empleadoState = empleadoState.copy(mail = e.mail)
            }

            EmpleadoFormEvent.OnDismissError -> {
                validacionEmpleadoState = ValidacionEmpleadoUiState()
            }

            is EmpleadoFormEvent.OnSaveEmpleado -> {
                validacionEmpleadoState = validacionEmpleado.valida(empleadoState)
                if (!validacionEmpleadoState.hayError) {
                    val empleado: Empleado = empleadoState.toEmpleado()
                    runBlocking {
                        if (editandoEmpleadoExistenteState) {
                            empleadosRepository.update(empleado)
                        } else {
                            empleadosRepository.insert(empleado)
                        }
                    }
                    e.onNavigateTrasFormEmpleado(true)
                }
            }
        }
    }
}