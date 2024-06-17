package com.cambiame.data.services.encargos

import android.util.Log
import com.cambiame.data.services.ApiServicesException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EncargosImplementation @Inject constructor(
    private val encargosService: EncargosService
) {
    private val logTag: String = "OkHttp"
    suspend fun get(): List<EncargosApi> {
        val mensajeError = "No se han podido obtener los contactos"
        try {
            val response = encargosService.encargos()
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                val dato = response.body()
                return dato ?: throw ApiServicesException("No hay datos")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        }
        catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
    }

    suspend fun get(id: Int): EncargosApi {
        val mensajeError = "No se han podido obtener el encargo con id = $id"
        try {
            val response = encargosService.encargos(id)
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                val dato = response.body()
                return dato ?: throw ApiServicesException("No hay datos")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
    }

    suspend fun insert(encargo: EncargosApi) {
        val mensajeError ="No se ha podido añadir el encargo"
        try {
            val response = encargosService.insert(encargo)
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                // Aquí response.body() es un objeto de tipo RespuestaApi
                // que simplemente logeamos si no es null.
                Log.d(logTag, response.body()?.toString() ?: "No hay respuesta")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
    }

    suspend fun update(encargo: EncargosApi) {
        val mensajeError = "No se ha podido actualizar el encargo"
        try {
            val response = encargosService.update(encargo)
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                // Aquí response.body() es un objeto de tipo RespuestaApi
                // que simplemente logeamos si no es null.
                Log.d(logTag, response.body()?.toString() ?: "No hay respuesta")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
    }

    suspend fun delete(id: Int) {
        val mensajeError = "No se ha podido borrar el encargo"
        try {
            val response = encargosService.delete(id)
            if (response.isSuccessful) {
                Log.d(logTag, response.toString())
                // Aquí response.body() es un objeto de tipo RespuestaApi
                // que simplemente logeamos si no es null.
                Log.d(logTag, response.body()?.toString() ?: "No hay respuesta")
            } else {
                val body = response.errorBody()?.string()
                Log.e(logTag, "$mensajeError (código ${response.code()}): $this\n${body}")
                throw ApiServicesException(mensajeError)
            }
        } catch (e: Exception) {
            Log.e(logTag, "Error: ${e.localizedMessage}")
            throw ApiServicesException(mensajeError)
        }
    }
}