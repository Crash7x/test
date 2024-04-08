package com.example.test.screens.characters.data.network

import com.example.test.common.data.mapper.CharacterMapper
import com.example.test.common.data.model.base.getResultOrThrowException
import com.example.test.common.domain.model.Character
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi,
    private val characterMapper: CharacterMapper
) : CharactersRepository {
    override suspend fun getCharacters(): List<Character> {
        val characters = charactersApi.getCharacters().getResultOrThrowException()
        return characters.map(characterMapper::transform)
    }

}