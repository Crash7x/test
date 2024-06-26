package com.example.test.screens.detail.character.di

import com.example.test.screens.detail.character.domain.usecase.GetCharacterDetailUseCase
import com.example.test.screens.detail.character.domain.usecase.GetCharacterDetailUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface CharacterDetailBindModule {

    @Binds
    fun bindGetCharacterDetailUseCase(usecase: GetCharacterDetailUseCaseImpl): GetCharacterDetailUseCase
}