package com.gabo.authretrofit.diModules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.gabo.authretrofit.constants.KEY
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val preferencesModule = module {
    single {
        providePreferences(androidApplication())
    }
}

fun providePreferences(app: Application): SharedPreferences {
    return app.getSharedPreferences(KEY, Context.MODE_PRIVATE)
}