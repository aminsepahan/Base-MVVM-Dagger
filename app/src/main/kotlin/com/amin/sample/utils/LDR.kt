package com.amin.sample.utils

// * LiveDataResult
@Suppress("unused")
class LDR<T>(val status: Status, val response: T? = null, val err: Throwable? = null) {
    companion object {
        fun <T> success(data: T) = LDR(Status.SUCCESS, data)
        fun <T> success() = LDR<T>(Status.SUCCESS)
        fun <T> loading() = LDR<T>(Status.LOADING)
        fun <T> started() = LDR<T>(Status.STARTED)
        fun <T> notLoggedIn() = LDR<T>(Status.AUTH_PROBLEM)
        fun <T> error(err: Throwable?, data: T? = null) = LDR(Status.ERROR, data, err)
        fun <T> error(err: String?) = LDR<T>(Status.ERROR, null, Throwable(err))
        fun <T> progress(data: T) = LDR(Status.PROGRESS, data)
    }

    enum class Status {
        SUCCESS, ERROR, LOADING, STARTED, PROGRESS, AUTH_PROBLEM
    }

}
