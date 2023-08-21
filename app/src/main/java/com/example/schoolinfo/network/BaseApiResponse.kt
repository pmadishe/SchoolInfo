package com.example.schoolinfo.network

import retrofit2.Response

abstract class AbstractedApiResponse {
    suspend fun <T> doSafeApiCall(apiCall: suspend () -> Response<T>): ApiResponse<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return ApiResponse.Success(body)
                }
            }

            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): ApiResponse<T> =
        ApiResponse.Error("Api call failed $errorMessage")
}