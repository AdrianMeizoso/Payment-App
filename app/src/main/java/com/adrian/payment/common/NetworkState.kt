package com.adrian.payment.common

sealed class NetworkState {
    object Loading: NetworkState()
    object Success: NetworkState()
    class Error(val throwable: Throwable): NetworkState()
}