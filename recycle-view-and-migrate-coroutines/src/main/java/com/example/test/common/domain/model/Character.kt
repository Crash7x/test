package com.example.test.common.domain.model

data class Character(
    val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val gender: Gender,
    val origin: Original,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String
)