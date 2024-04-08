package com.example.test.common.data.model.base

import com.example.test.common.data.model.error.NetworkException
import com.example.test.common.data.model.error.UnknownException
import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val info: Info? = null,
    val results: List<T>? = null,
    val error: String? = null
)

fun <T> Response<T>.getResultOrThrowException(): List<T> {
    if (results == null) throw NetworkException(error ?: throw UnknownException())
    return results
}