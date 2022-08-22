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
) : ViewModel() {

}