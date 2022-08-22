package com.gabo.authretrofit.diModules

import com.gabo.authretrofit.ui.home.HomeViewModel
import com.gabo.authretrofit.ui.login.LoginViewModel
import com.gabo.authretrofit.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        LoginViewModel(get(), get(), get())
    }
    viewModel {
        RegisterViewModel()
    }
    viewModel{
        HomeViewModel(get())
    }
}