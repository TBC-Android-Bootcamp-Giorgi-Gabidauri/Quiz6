package com.gabo.authretrofit

import android.app.Application
import com.gabo.authretrofit.diModules.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class AuthRetrofitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(provideKoinModules())
        }
    }

    private fun provideKoinModules(): List<Module> {
        return listOf(
            repositoryModule,
            viewModelsModule,
            useCasesModule,
            retrofitModule,
            preferencesModule
        )
    }
}