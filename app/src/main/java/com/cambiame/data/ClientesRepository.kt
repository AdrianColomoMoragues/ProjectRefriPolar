package com.cambiame.data

import com.cambiame.data.services.clientes.ClientesImplementation
import com.cambiame.models.Clientes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClientesRepository @Inject constructor(
    private val clientesService: ClientesImplementation,
) {
    suspend fun get(): List<Clientes> = withContext(Dispatchers.IO) {
        clientesService.get().map { it.toClientes() }
    }

    suspend fun get(id: Int): Clientes = withContext(Dispatchers.IO) {
        clientesService.get(id).toClientes()
    }
    suspend fun insert(cliente: Clientes) = withContext(Dispatchers.IO) {
        clientesService.insert(cliente.toClienteApi())
    }
    suspend fun update(cliente: Clientes) = withContext(Dispatchers.IO) {
        clientesService.update(cliente.toClienteApi())
    }
    suspend fun delete(id: Int) = withContext(Dispatchers.IO){
        clientesService.delete(id)
    }
}