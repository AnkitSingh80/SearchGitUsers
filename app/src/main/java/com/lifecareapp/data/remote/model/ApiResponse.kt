package com.lifecareapp.data.remote.model

data class ApiResponse<out T>(
        val message: String? = null,
        val data: T? = null)