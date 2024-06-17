package com.cambiame.data.services.categoriaprofesional

import android.util.Log
import com.cambiame.data.services.ApiServicesException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriaProfesionalImplementation @Inject constructor(
    private val categoriaProfesionalService: CategoriaProfesionalService,
) {
    private val logTag: String = "OkHttp"
    suspend fun get(): List<CategoriaProfesionalApi> {
        val mensajeError = "No se han podido obtener los contactos"
        try {
            val response = categoriaProfesionalService.categoriasprofesionales()
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

    suspend fun get(cod: String): CategoriaProfesionalApi{
        val mensajeError = "No se han podido obtener la categoria profesional con codigo = $cod"
        try {
            val response = categoriaProfesionalService.categoriasprofesionales(cod)
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
}