package com.example.test.screens.detail.character.domain.usecase

import com.example.test.common.domain.model.Character

interface GetCharacterDetailUseCase {
    suspend fun getCharacterDetail(id: Int): Character
}