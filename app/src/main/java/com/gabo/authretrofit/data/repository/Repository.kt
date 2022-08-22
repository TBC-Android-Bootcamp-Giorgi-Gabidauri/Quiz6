package com.gabo.authretrofit.data.repository

import com.gabo.authretrofit.data.models.LoginModel
import com.gabo.authretrofit.data.models.RegisterModel
import com.gabo.authretrofit.data.models.RequestModel
import retrofit2.Response

interface Repository {
    suspend fun registerUser(requestModel: RequestModel): Response<RegisterModel>
    suspend fun loginUser(requestModel: RequestModel): Response<LoginModel>
    suspend fun saveLogInStatus(isLoggedIn: Boolean)
    suspend fun getLogInStatus(): Boolean
    suspend fun deleteLoginStatus()
}