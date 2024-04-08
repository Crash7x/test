package com.example.test.screens.characters.domain.usecase

import com.example.test.screens.characters.data.network.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : GetCharactersUseCase {
    override suspend fun getCharacters() = charactersRepository.getCharacters()
}