package com.lifecareapp.data.local


sealed class AppError {
    data class ApiError(val message: String) : AppError()
    data class ApiUnauthorized(val message: String) : AppError()
    data class ApiFailure(val message: String) : AppError()
    
}