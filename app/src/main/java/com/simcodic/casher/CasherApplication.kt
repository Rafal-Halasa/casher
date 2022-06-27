package com.simcodic.casher

import android.app.Application
import com.simcodic.casher.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class CasherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@CasherApplication)
            modules(viewModules, viewBindings, retrofit, services, moshi, interceptors, adapters)
        }

    }
}