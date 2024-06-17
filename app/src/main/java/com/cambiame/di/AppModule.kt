package com.cambiame.di

import com.cambiame.data.services.categoriaprofesional.CategoriaProfesionalService
import com.cambiame.data.services.clientes.ClientesService
import com.cambiame.data.services.empleados.EmpleadosService
import com.cambiame.data.services.encargos.EncargosService
import com.cambiame.data.services.interceptors.AlamcenDeCookies
import com.cambiame.data.services.interceptors.EnviaCookiesInterceptor
import com.cambiame.data.services.interceptors.ReciveCookiesInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule{
    @Provides
    @Singleton
    fun provideOkHttpClient(
        almacenDeCookies: AlamcenDeCookies,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        val timeout = 10L
        return OkHttpClient.Builder()
            .addInterceptor(EnviaCookiesInterceptor(almacenDeCookies))
            .addInterceptor(ReciveCookiesInterceptor(almacenDeCookies))

            .addInterceptor(loggingInterceptor)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://refripolar.westeurope.cloudapp.azure.com:8080/apirefripolarserver/data/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideClientesService(
        retrofit: Retrofit,
    ): ClientesService = retrofit.create(ClientesService::class.java)

    @Provides
    @Singleton
    fun provideCategoriaProfesionalService(
        retrofit: Retrofit,
    ): CategoriaProfesionalService = retrofit.create(CategoriaProfesionalService::class.java)

    @Provides
    @Singleton
    fun provideEmpleadosService(
        retrofit: Retrofit,
    ): EmpleadosService = retrofit.create(EmpleadosService::class.java)

    @Provides
    @Singleton
    fun provideEncargosService(
        retrofit: Retrofit,
    ): EncargosService = retrofit.create(EncargosService::class.java)
}