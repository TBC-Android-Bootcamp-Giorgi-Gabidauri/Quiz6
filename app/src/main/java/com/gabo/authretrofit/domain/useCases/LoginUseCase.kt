package com.gabo.authretrofit.domain.useCases

import com.gabo.authretrofit.base.BaseUseCase
import com.gabo.authretrofit.data.models.LoginModel
import com.gabo.authretrofit.data.models.RequestModel
import com.gabo.authretrofit.data.repository.Repository
import retrofit2.Response

class LoginUseCase(private val repository: Repository) :
    BaseUseCase<RequestModel, Response<LoginModel>>() {
    override suspend fun invoke(params: RequestModel): Response<LoginModel> {
        return repository.loginUser(params)
    }
}