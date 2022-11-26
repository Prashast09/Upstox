package com.trade.network

/**
 * Wrapper representing state of network response
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message)
    class ApiError<T>(
        message: String?,
        data: T? = null
    ) : Resource<T>(data, message)

    override fun toString(): String = "${this::class.simpleName} ($data, $message)"
}