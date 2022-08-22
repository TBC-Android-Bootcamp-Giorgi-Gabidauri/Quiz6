package com.gabo.authretrofit.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabo.authretrofit.data.models.RequestModel
import com.gabo.authretrofit.domain.useCases.GetLoginStatusUseCase
import com.gabo.authretrofit.domain.useCases.LoginUseCase
import com.gabo.authretrofit.domain.useCases.SaveLoginStatusUseCase
import com.gabo.authretrofit.helpers.handleResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val saveLoginStatusUseCase: SaveLoginStatusUseCase,
    private val getLoginStatusUseCase: GetLoginStatusUseCase
) : ViewModel() {
    val loginStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        getLoginStatus()
    }

    private fun getLoginStatus() {
        viewModelScope.launch {
            loginStatus.value = getLoginStatusUseCase(Unit)
        }
    }

    fun requestLogin(requestModel: RequestModel) = flow {
        handleResponse(loginUseCase(requestModel)).collect { emit(it) }
    }

    fun saveLoginStatus() {
        viewModelScope.launch {
            saveLoginStatusUseCase(true)
        }
    }
}
