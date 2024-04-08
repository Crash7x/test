package com.example.test.screens.characters.domain.usecase

import com.example.test.common.domain.model.Character

interface GetCharactersUseCase {
    suspend fun getCharacters(): List<Character>
}