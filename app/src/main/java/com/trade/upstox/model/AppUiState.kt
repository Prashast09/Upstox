package com.trade.upstox.model

/**
 * Class representing states of the response returned from use-cases
 */
sealed class AppUiState<T> {
    class Success<T>(val response: T) : AppUiState<T>()
    class Error<T>(val msg: String) : AppUiState<T>()
    class Start<T> : AppUiState<T>()
    class Loading<T> : AppUiState<T>()
}