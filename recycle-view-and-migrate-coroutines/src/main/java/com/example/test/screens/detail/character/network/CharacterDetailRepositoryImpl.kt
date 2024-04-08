package com.example.test.screens.detail.character.network

import com.example.test.common.data.mapper.CharacterMapper
import com.example.test.common.data.model.base.getResultOrThrowException
import com.example.test.common.domain.model.Character
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(
    private val characterDetailApi: CharacterDetailApi,
    private val characterMapper: CharacterMapper
) : CharacterDetailRepository {

    override suspend fun getCharacterDetail(id: Int): Character {
        val character = characterDetailApi.getCharacterDetail(id)
        return characterMapper.transform(character)
    }

}