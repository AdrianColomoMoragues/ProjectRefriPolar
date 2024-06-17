package com.cambiame.data.services.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class ReciveCookiesInterceptor(
    private val almacenDeCookies: AlamcenDeCookies
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain) : Response {
        val originalResponse = chain.proceed(chain.request())
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            val cookies = HashSet<String>()
            for (header in originalResponse.headers("Set-Cookie")) {
                cookies.add(header)
            }
            almacenDeCookies.setCookies(cookies)
        }
        // Devolvemos la respuesta original
        return originalResponse
    }
}