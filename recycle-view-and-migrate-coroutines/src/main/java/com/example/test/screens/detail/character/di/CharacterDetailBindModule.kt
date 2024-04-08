package com.example.test.screens.detail.character.di

import com.example.test.screens.detail.character.domain.usecase.GetCharacterDetailUseCase
import com.example.test.screens.detail.character.domain.usecase.GetCharacterDetailUseCaseImpl
import com.example.test.screens.detail.character.network.CharacterDetailRepository
import com.example.test.screens.detail.character.network.CharacterDetailRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface CharacterDetailBindModule {

    @Binds
    fun bindCharacterDetailRepository(repository: CharacterDetailRepositoryImpl): CharacterDetailRepository

    @Binds
    fun bindGetCharacterDetailUseCase(usecase: GetCharacterDetailUseCaseImpl): GetCharacterDetailUseCase
}