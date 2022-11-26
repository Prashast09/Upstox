package com.trade.network

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

sealed class ApiResult<T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class ApiError<T>(val code: Int, val error: String?) : ApiResult<T>()
    data class NetworkError<T>(val exception: Exception) : ApiResult<T>()

    fun <R> toResource(transformSuccess: (data: T) -> R): Resource<R> = when (this) {
        is Success -> Resource.Success(transformSuccess(data))
        is ApiError -> Resource.ApiError(this.error)
        is NetworkError -> Resource.Error(this.exception.localizedMessage)
    }
}

@Suppress("UNCHECKED_CAST")
suspend fun <T> apiCall(apiCall: suspend () -> Call<T>): ApiResult<T> {
    val response = try {
        apiCall().execute()
    } catch (e: Exception) {
        return ApiResult.NetworkError(e)
    }

    return if (response.isSuccessful) {
        val body = response.body()
        if (body == null) {
            ApiResult.Success(null as T)
        } else {
            ApiResult.Success(body)
        }
    } else {
        ApiResult.ApiError(
            code = response.code(),
            error = parseApiError(response)
        )
    }
}

private fun parseApiError(response: Response<*>): String? {
    try {
        val result = Gson().fromJson(response.errorBody()?.string(), String::class.java)
        if (result.isEmpty()) return null
        return result
    } catch (e: Exception) {
        return null
    }
}