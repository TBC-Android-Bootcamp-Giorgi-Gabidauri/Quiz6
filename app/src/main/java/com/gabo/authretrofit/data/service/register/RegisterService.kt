package com.gabo.authretrofit.data.service.register

import com.gabo.authretrofit.data.models.RegisterModel
import com.gabo.authretrofit.data.models.RequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST(REGISTER_END_POINT)
    suspend fun register(@Body requestModel: RequestModel): Response<RegisterModel>

    companion object {
        const val REGISTER_END_POINT = "register"
    }
}