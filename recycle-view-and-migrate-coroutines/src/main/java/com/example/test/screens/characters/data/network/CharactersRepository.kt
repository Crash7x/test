package com.example.test.screens.characters.data.network

import com.example.test.common.domain.model.Character

interface CharactersRepository {
    suspend fun getCharacters(): List<Character>
}