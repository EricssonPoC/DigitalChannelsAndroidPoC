package com.dfmovies.android.core.domain.error

import com.dfmovies.android.core.data.model.ErrorResponse
import com.dfmovies.android.core.domain.model.RError


object ErrorMapper {

    fun mapOnResponse(response: ErrorResponse?): RError {
        return RError(
            errorCode = response?.status_code.toString(),
            message = response?.status_message.orEmpty()
        )
    }

}