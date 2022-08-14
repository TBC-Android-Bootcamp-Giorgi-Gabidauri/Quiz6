package com.gabo.authretrofit.diModules

import com.gabo.authretrofit.ui.login.LoginViewModel
import com.gabo.authretrofit.ui.register.RegisterViewModel
import com.gabo.authretrofit.ui.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        LoginViewModel(get(), get())
    }

    viewModel {
        RegisterViewModel(get(), get())
    }
    viewModel {
        WelcomeViewModel(get())
    }
}