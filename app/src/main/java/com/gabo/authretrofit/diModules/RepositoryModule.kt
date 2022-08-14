package com.gabo.authretrofit.diModules

import com.gabo.authretrofit.data.repository.Repository
import com.gabo.authretrofit.data.repository.RepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single {
        RepositoryImpl(get(), get(), get())
    } bind Repository::class
}