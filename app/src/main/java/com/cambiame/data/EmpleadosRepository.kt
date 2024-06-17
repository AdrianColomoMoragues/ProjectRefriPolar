package com.cambiame.data

import com.cambiame.data.services.empleados.EmpleadosImplementation
import com.cambiame.models.Empleado
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmpleadosRepository @Inject constructor(
    private val empleadosService: EmpleadosImplementation,
) {
    suspend fun get(): List<Empleado> = withContext(Dispatchers.IO) {
        empleadosService.get().map { it.toEmpleado() }
    }

    suspend fun get(id: Int) : Empleado = withContext(Dispatchers.IO) {
        empleadosService.get(id).toEmpleado()
    }
    suspend fun insert(empleado: Empleado) = withContext(Dispatchers.IO) {
        empleadosService.insert(empleado.toEmpleadosApi())
    }
    suspend fun update(empleado: Empleado) = withContext(Dispatchers.IO) {
        empleadosService.update(empleado.toEmpleadosApi())
    }
    suspend fun delete(id: Int) = withContext(Dispatchers.IO){
        empleadosService.delete(id)
    }
}