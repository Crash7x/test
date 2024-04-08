package com.example.test.common.data.model.base

import kotlinx.serialization.Serializable

@Serializable
data class Info(
    var count: Int?,
    var pages: Int?,
    var next: String?,
    var prev: String?
)