package com.gabo.authretrofit.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabo.authretrofit.domain.useCases.GetLoginStatusUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WelcomeViewModel(private val getLoginStatusUseCase: GetLoginStatusUseCase) : ViewModel() {
    val loginStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        getLoginStatus()
    }

    private fun getLoginStatus() {
        viewModelScope.launch {
            loginStatus.value = getLoginStatusUseCase(Unit)
        }
    }
}