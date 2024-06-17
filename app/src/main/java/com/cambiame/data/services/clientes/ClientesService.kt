package com.cambiame.data.services.clientes

import com.cambiame.data.services.RespuestaApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClientesService {
    @GET("clientes")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun clientes(): Response<List<ClientesApi>>

    @GET("clientes/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun clientes(@Path("id") id: Int): Response<ClientesApi>

    @POST("clientes")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun insert(@Body e: ClientesApi): Response<RespuestaApi>

    @PUT("clientes")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun update(@Body e: ClientesApi): Response<RespuestaApi>

    @DELETE("clientes/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun delete(@Path("id") id: Int): Response<RespuestaApi>
}