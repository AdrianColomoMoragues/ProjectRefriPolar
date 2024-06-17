package com.cambiame.data.services.encargos

import com.cambiame.data.services.RespuestaApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EncargosService {
    @GET("encargos")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun encargos(): Response<List<EncargosApi>>

    @GET("encargos/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun encargos(@Path("id") id: Int): Response<EncargosApi>

    @POST("encargos")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun insert(@Body e: EncargosApi) : Response<RespuestaApi>

    @PUT("encargos")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun update(@Body e: EncargosApi) : Response<RespuestaApi>

    @DELETE("encargos/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun delete(@Path("id") id: Int) : Response<RespuestaApi>
}