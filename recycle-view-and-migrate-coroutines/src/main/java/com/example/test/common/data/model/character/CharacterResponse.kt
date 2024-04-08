package com.example.test.common.data.model.character

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginalResponse,
    val location: LocationResponse,
    val image: String,
    val episode: List<String>,
    val url: String
)