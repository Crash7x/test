package com.example.test.screens.detail.character.network

import com.example.test.common.domain.model.Character

interface CharacterDetailRepository {
    suspend fun getCharacterDetail(id: Int): Character
}