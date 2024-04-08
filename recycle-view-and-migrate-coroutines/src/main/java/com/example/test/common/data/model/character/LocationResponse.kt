package com.example.test.common.data.model.character

import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val name: String,
    val url: String
)