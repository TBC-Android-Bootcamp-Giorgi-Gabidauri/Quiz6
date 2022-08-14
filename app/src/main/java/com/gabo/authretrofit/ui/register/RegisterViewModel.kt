package com.gabo.authretrofit.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabo.authretrofit.data.models.RequestModel
import com.gabo.authretrofit.domain.useCases.RegisterUseCase
import com.gabo.authretrofit.domain.useCases.SaveLoginStatusUseCase
import com.gabo.authretrofit.helpers.handleResponse
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val saveLoginStatusUseCase: SaveLoginStatusUseCase,
) : ViewModel() {
    fun requestRegister(requestModel: RequestModel) = flow {
        handleResponse(registerUseCase(requestModel)).collect { emit(it) }
    }

    fun saveLoginStatus() {
        viewModelScope.launch {
            saveLoginStatusUseCase(true)
        }
    }
}