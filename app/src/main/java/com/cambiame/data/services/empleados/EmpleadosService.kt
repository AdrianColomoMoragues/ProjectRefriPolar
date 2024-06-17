package com.cambiame.data.services.empleados

import com.cambiame.data.services.RespuestaApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EmpleadosService {
    @GET("empleados")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun empleados(): Response<List<EmpleadosApi>>

    @GET("empleados/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun empleados(@Path("id") id: Int): Response<EmpleadosApi>

    @POST("empleados")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun insert(@Body e: EmpleadosApi) : Response<RespuestaApi>

    @PUT("empleados")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun update(@Body e: EmpleadosApi) : Response<RespuestaApi>

    @DELETE("empleados/{id}")
    @Headers("Accept: application/json", "Content-Type: application/json")
    suspend fun delete(@Path("id") id: Int) : Response<RespuestaApi>
}