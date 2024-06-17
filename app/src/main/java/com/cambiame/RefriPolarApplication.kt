package com.cambiame

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking

@HiltAndroidApp
class RefriPolarApplication : Application() {

    override fun onCreate(){
        super.onCreate()

        runBlocking {

        }
    }
}