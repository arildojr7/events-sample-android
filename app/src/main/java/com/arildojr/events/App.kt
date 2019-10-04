package com.arildojr.events

import android.app.Application
import com.arildojr.data.di.getEventModules
import com.arildojr.events.eventdetail.di.getEventDetailModules
import com.arildojr.events.main.di.getMainModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(getEventModules() + getMainModules() + getEventDetailModules())
        }
    }
}