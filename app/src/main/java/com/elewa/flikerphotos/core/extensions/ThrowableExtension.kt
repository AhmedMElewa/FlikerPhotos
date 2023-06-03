package com.elewa.flikerphotos.core.extensions


import com.elewa.flikerphotos.core.data.constants.ErrorMessages
import com.elewa.flikerphotos.core.data.model.ErrorResponse
import java.io.IOException

fun Throwable.parseError(): ErrorResponse {
    var errorResponse = ErrorResponse(ErrorMessages.SOMETHING_WENT_WRONG)
    when (this) {
        is IOException -> {
            errorResponse = ErrorResponse(ErrorMessages.INTERNET_CONNECTION_ERROR)
        }
    }
    return errorResponse
}
