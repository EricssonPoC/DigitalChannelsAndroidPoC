package com.dfmovies.android.core.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status_code")
    val status_code: Int?,
    @SerializedName("status_message")
    val status_message: String?,
    @SerializedName("success")
    val success: Boolean?
)