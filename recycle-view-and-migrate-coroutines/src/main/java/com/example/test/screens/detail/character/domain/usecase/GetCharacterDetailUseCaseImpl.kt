package com.example.test.screens.detail.character.domain.usecase

import com.example.test.screens.detail.character.network.CharacterDetailRepository
import javax.inject.Inject

class GetCharacterDetailUseCaseImpl @Inject constructor(
    private val characterDetailRepository: CharacterDetailRepository
) : GetCharacterDetailUseCase {
    override suspend fun getCharacterDetail(id: Int) = characterDetailRepository.getCharacterDetail(id)
}