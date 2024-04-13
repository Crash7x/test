package com.example.test.common.data.network.character

import com.example.test.common.data.mapper.character.CharacterMapper
import com.example.test.common.data.model.base.getResultOrThrowException
import com.example.test.common.domain.model.character.Character
import io.reactivex.Single
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: CharactersApi,
    private val characterMapper: CharacterMapper
) : CharactersRepository {
    override fun getCharacters(): Single<List<Character>> {
        return charactersApi.getCharacters()
            .map { it.getResultOrThrowException() }
            .map { it.map(characterMapper::transform) }
    }

    override fun getSingleCharacter(id: Int): Single<Character> {
        return charactersApi.getSingleCharacters(id)
            .map(characterMapper::transform)
    }

    override fun getCharacterIds(id: List<Int>): Single<List<Character>> {
        return charactersApi.getCharactersIds(id)
            .map { it.map(characterMapper::transform) }
    }

}