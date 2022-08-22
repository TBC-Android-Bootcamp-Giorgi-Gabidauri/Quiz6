package com.gabo.authretrofit.diModules

import com.gabo.authretrofit.domain.useCases.*
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
    factory {
        DeleteLoginStatusUseCase(get())
    }
}