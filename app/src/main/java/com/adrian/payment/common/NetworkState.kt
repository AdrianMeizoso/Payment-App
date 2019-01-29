package com.adrian.payment.common

enum class Status {
    RUNNING,
    LOADED,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
        val status: Status,
        val msg: String? = null) {
    companion object {
        val SUCCESS = NetworkState(Status.LOADED)
        val LOADING = NetworkState(Status.RUNNING)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}