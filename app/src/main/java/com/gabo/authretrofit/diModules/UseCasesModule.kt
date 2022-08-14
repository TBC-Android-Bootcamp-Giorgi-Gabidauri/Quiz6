package com.gabo.authretrofit.diModules

import com.gabo.authretrofit.domain.useCases.GetLoginStatusUseCase
import com.gabo.authretrofit.domain.useCases.LoginUseCase
import com.gabo.authretrofit.domain.useCases.RegisterUseCase
import com.gabo.authretrofit.domain.useCases.SaveLoginStatusUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory {
        LoginUseCase(get())
    }
    factory {
        RegisterUseCase(get())
    }
    factory {
        GetLoginStatusUseCase(get())
    }
    factory {
        SaveLoginStatusUseCase(get())
    }
}