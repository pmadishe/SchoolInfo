package com.example.schoolinfo.network

//data class ApiResponse<T>(
//    var results: SchoolInfoModel? = null,
//    val error: Throwable? = null
//)

sealed class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ApiResponse<T>(data)
    class Error<T>(message: String, data: T? = null) : ApiResponse<T>(data, message)
    class Loading<T> : ApiResponse<T>()
}
