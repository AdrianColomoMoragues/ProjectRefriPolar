package com.cambiame.data.services.categoriaprofesional

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CategoriaProfesionalService {
    @GET("categoriasprofesionales")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun categoriasprofesionales(): Response<List<CategoriaProfesionalApi>>

    @GET("categoriasprofesionales/{cod}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun categoriasprofesionales(@Path("cod") cod: String): Response<CategoriaProfesionalApi>
}
