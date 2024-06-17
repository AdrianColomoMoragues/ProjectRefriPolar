package com.cambiame.data

import com.cambiame.data.services.categoriaprofesional.CategoriaProfesionalImplementation
import com.cambiame.models.CategoriaProfesional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoriaProfesionalRepository @Inject constructor(
    private val categoriaProfesionalService: CategoriaProfesionalImplementation) {
    suspend fun get(): List<CategoriaProfesional> = withContext(Dispatchers.IO) {
        categoriaProfesionalService.get().map { it.toCategoriaProfesional() }
    }
}
