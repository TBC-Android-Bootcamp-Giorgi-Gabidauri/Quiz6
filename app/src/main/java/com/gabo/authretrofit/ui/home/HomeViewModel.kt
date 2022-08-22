package com.gabo.authretrofit.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabo.authretrofit.domain.useCases.DeleteLoginStatusUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val deleteLoginStatusUseCase: DeleteLoginStatusUseCase) : ViewModel() {
    fun deleteLoginStatus(){
        viewModelScope.launch {
            deleteLoginStatusUseCase(false)
        }
    }
}