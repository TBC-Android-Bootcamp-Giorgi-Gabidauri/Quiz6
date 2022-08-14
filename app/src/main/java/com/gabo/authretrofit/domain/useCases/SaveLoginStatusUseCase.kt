package com.gabo.authretrofit.domain.useCases

import com.gabo.authretrofit.base.BaseUseCase
import com.gabo.authretrofit.data.repository.Repository

class SaveLoginStatusUseCase(private val repository: Repository) : BaseUseCase<Boolean, Unit>() {
    override suspend fun invoke(params: Boolean) {
        repository.saveLogInStatus(params)
    }
}