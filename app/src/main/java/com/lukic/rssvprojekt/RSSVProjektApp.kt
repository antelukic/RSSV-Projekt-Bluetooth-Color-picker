package com.lukic.rssvprojekt

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RSSVProjektApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RSSVProjektApp)
            modules(
                listOf(
                    appModule
                )
            )
        }
    }
}
