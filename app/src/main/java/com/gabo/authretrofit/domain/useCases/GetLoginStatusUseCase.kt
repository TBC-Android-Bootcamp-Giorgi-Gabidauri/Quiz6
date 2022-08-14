package com.gabo.authretrofit.domain.useCases

import com.gabo.authretrofit.base.BaseUseCase
import com.gabo.authretrofit.data.repository.Repository

class GetLoginStatusUseCase(private val repository: Repository): BaseUseCase<Unit,Boolean>() {
    override suspend fun invoke(params: Unit): Boolean {
       return repository.getLogInStatus()
    }
}