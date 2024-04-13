package com.example.test.screens.characters.domain.usecase

import com.example.test.common.data.network.character.CharactersRepository
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.rx2.await
import javax.inject.Inject

class GetCharactersUseCaseImpl @Inject constructor(
    private val charactersRepository: CharactersRepository
) : GetCharactersUseCase {
    override suspend fun getCharacters() =
        charactersRepository.getCharacters().subscribeOn(Schedulers.io()).await()
}