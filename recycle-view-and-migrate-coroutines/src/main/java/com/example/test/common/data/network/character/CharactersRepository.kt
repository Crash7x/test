package com.example.test.common.data.network.character

import com.example.test.common.domain.model.character.Character

interface CharactersRepository {
    suspend fun getCharacters(): List<Character>

    suspend fun getSingleCharacter(id: Int): Character

    suspend fun getCharacterIds(id: List<Int>): List<Character>
}