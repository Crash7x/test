package com.example.test.common.data.network.character

import com.example.test.common.data.mapper.character.CharacterMapper
import com.example.test.common.data.model.base.getResultOrThrowException
import com.example.test.common.domain.model.character.Character
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi,
    private val characterMapper: CharacterMapper
) : CharactersRepository {
    override suspend fun getCharacters(): List<Character> {
        val characters = charactersApi.getCharacters().getResultOrThrowException()
        return characters.map(characterMapper::transform)
    }

    override suspend fun getSingleCharacter(id: Int): Character {
        val character = charactersApi.getSingleCharacters(id)
        return characterMapper.transform(character)
    }

    override suspend fun getCharacterIds(id: List<Int>): List<Character> {
        val characters = charactersApi.getCharactersIds(id)
        return characters.map(characterMapper::transform)
    }

}