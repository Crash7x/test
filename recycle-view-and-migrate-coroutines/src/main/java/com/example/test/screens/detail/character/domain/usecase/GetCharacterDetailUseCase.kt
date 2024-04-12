package com.example.test.screens.detail.character.domain.usecase

import com.example.test.screens.detail.character.domain.model.CharacterDetail

interface GetCharacterDetailUseCase {
    suspend fun getCharacterDetail(id: Int): CharacterDetail
}