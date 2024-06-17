package com.cambiame.data.services.interceptors

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlamcenDeCookies @Inject constructor() {
    private var cookies: HashSet<String>? = null
    fun getCookies() : HashSet<String> ? = cookies
    fun setCookies(cookies: HashSet<String>) {
        this.cookies = cookies
    }
}