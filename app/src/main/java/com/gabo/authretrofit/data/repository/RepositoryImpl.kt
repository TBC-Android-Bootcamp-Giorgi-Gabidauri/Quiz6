package com.gabo.authretrofit.data.repository

import android.content.SharedPreferences
import com.gabo.authretrofit.constants.LOGIN_STATUS_KEY
import com.gabo.authretrofit.data.models.LoginModel
import com.gabo.authretrofit.data.models.RegisterModel
import com.gabo.authretrofit.data.models.RequestModel
import com.gabo.authretrofit.data.service.login.LoginService
import com.gabo.authretrofit.data.service.register.RegisterService
import retrofit2.Response

class RepositoryImpl(
    private val loginService: LoginService,
    private val registerService: RegisterService,
    private val preferences: SharedPreferences
) : Repository {
    override suspend fun registerUser(requestModel: RequestModel): Response<RegisterModel> {
        return registerService.register(requestModel)
    }

    override suspend fun loginUser(requestModel: RequestModel): Response<LoginModel> {
        return loginService.login(requestModel)
    }

    override suspend fun saveLogInStatus(isLoggedIn: Boolean) {
        preferences.edit().putBoolean(LOGIN_STATUS_KEY, isLoggedIn).apply()
    }

    override suspend fun getLogInStatus(): Boolean {
        return preferences.getBoolean(LOGIN_STATUS_KEY, false)
    }

    override suspend fun deleteLoginStatus(isLoggedIn: Boolean) {
        preferences.edit().putBoolean(LOGIN_STATUS_KEY, isLoggedIn).apply()
    }

}