package com.dfmovies.android.core.data.errorhandler

import com.dfmovies.android.core.domain.model.RError


class NetworkException(val error: RError, val code: Int) : Exception(error.message)