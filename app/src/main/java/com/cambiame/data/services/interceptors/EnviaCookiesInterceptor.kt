package com.cambiame.data.services.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class EnviaCookiesInterceptor(
    private val almacenDeCookies: AlamcenDeCookies
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val cookies = almacenDeCookies.getCookies()
        if (cookies != null) {
            for (cookie in cookies) {
                // Añadimos las cookies a la cabecera Cookie de la
                // peticion en el builder
                builder.addHeader("Cookie", cookie)
            }
        }
        return chain.proceed(builder.build())
    }
}