package com.gabo.authretrofit.domain.useCases

import com.gabo.authretrofit.base.BaseUseCase
import com.gabo.authretrofit.data.repository.Repository

class DeleteLoginStatusUseCase(private val repository: Repository): BaseUseCase<Unit,Unit>() {
    override suspend fun invoke(params: Unit) {
        repository.deleteLoginStatus()
    }

}
