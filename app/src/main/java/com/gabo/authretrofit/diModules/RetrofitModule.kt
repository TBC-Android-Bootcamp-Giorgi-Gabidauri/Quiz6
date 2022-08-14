package com.gabo.authretrofit.diModules

import com.gabo.authretrofit.constants.BASE_URL
import com.gabo.authretrofit.data.service.login.LoginService
import com.gabo.authretrofit.data.service.register.RegisterService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    factory {
        provideLoginService(get())
    }
    factory {
        provideRegisterService(get())
    }
    single {
        provideRetrofit()
    }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideLoginService(retrofit: Retrofit): LoginService {
    return retrofit.create(LoginService::class.java)
}

fun provideRegisterService(retrofit: Retrofit): RegisterService {
    return retrofit.create(RegisterService::class.java)
}