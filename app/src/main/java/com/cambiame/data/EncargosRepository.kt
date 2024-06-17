package com.cambiame.data

import com.cambiame.data.services.encargos.EncargosImplementation
import com.cambiame.models.Encargo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EncargosRepository @Inject constructor(
    private val encargosService: EncargosImplementation,
) {
    suspend fun get(): List<Encargo> = withContext(Dispatchers.IO) {
        encargosService.get().map { it.toEncargo() }
    }

    suspend fun get(id: Int): Encargo = withContext(Dispatchers.IO) {
        encargosService.get(id).toEncargo()
    }
    suspend fun insert(encargo: Encargo) = withContext(Dispatchers.IO) {
        encargosService.insert(encargo.toEncargoApi())
    }
    suspend fun update(encargo: Encargo) = withContext(Dispatchers.IO) {
        encargosService.update(encargo.toEncargoApi())
    }
    suspend fun delete(id: Int) = withContext(Dispatchers.IO){
            encargosService.delete(id)
    }
}