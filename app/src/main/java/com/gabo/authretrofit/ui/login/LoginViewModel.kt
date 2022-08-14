package com.gabo.authretrofit.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabo.authretrofit.data.models.RequestModel
import com.gabo.authretrofit.domain.useCases.LoginUseCase
import com.gabo.authretrofit.domain.useCases.SaveLoginStatusUseCase
import com.gabo.authretrofit.helpers.handleResponse
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val saveLoginStatusUseCase: SaveLoginStatusUseCase,
) : ViewModel() {
    //    fun requestLogin(requestModel: RequestModel) = flow {
//        val response = loginUseCase(requestModel)
//        val result = when {
//            response.isSuccessful -> {
//                val body = response.body()
//                ResponseHandler.Success(body)
//            }
//            else -> {
//                val errorMsg = response.errorBody()?.string()
//                ResponseHandler.Error(errorMsg)
//            }
//        }
//        emit(result)
//    }
    fun requestLogin(requestModel: RequestModel) = flow {
        handleResponse(loginUseCase(requestModel)).collect { emit(it) }
    }

    fun saveLoginStatus() {
        viewModelScope.launch {
            saveLoginStatusUseCase(true)
        }
    }
}
