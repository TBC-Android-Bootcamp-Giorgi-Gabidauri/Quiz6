package com.gabo.authretrofit.data.service.login

import com.gabo.authretrofit.data.models.LoginModel
import com.gabo.authretrofit.data.models.RequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST(LOGIN_END_POINT)
    suspend fun login(@Body requestModel: RequestModel): Response<LoginModel>

    companion object {
        const val LOGIN_END_POINT = "login"
    }
}