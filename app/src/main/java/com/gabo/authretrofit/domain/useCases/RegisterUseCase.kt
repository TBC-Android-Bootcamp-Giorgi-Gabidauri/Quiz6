package com.gabo.authretrofit.domain.useCases

import com.gabo.authretrofit.base.BaseUseCase
import com.gabo.authretrofit.data.models.RegisterModel
import com.gabo.authretrofit.data.models.RequestModel
import com.gabo.authretrofit.data.repository.Repository
import retrofit2.Response

class RegisterUseCase(private val repository: Repository) :
    BaseUseCase<RequestModel, Response<RegisterModel>>() {
    override suspend fun invoke(params: RequestModel): Response<RegisterModel> {
        return repository.registerUser(params)
    }
}