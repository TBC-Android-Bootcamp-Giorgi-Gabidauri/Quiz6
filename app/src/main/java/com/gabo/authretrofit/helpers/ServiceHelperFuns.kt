package com.gabo.authretrofit.helpers

import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <M> handleResponse(response: Response<M>) = flow {
    val result = when {
        response.isSuccessful -> {
            val body = response.body()
            ResponseHandler.Success(body!!)
        }
        else -> {
            val errorMsg = response.errorBody()?.string()
            ResponseHandler.Error(errorMsg)
        }
    }
    emit(result)
}

