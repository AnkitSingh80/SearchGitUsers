package com.lifecareapp.data.remote

import com.lifecareapp.data.local.AppError
import org.json.JSONObject

object ApiUtils {
    private fun getErrorMessage(errorJson: String?): String {
        if (errorJson.isNullOrBlank()) {
            return ""
        }

        return try {
            val errorJsonObject = JSONObject(errorJson)
            errorJsonObject.getString("message")
        } catch (exception: Exception) {
            ""
        }
    }

    fun getError(statusCode: Int, errorJson: String?): AppError {
        val message = getErrorMessage(errorJson)
        return if (statusCode == 401) {
            AppError.ApiUnauthorized(message)
        } else {
            AppError.ApiError(message)
        }
    }

    fun failure(throwable: Throwable): AppError {
        return AppError.ApiFailure(throwable.localizedMessage ?: "")
    }
}