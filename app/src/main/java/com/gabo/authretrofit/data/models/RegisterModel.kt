package com.gabo.authretrofit.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterModel(
    val id: Int?,
    val token: String?
): Parcelable
