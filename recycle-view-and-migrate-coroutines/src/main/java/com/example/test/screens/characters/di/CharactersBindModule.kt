package com.example.test.screens.characters.di

import com.example.test.screens.characters.data.network.CharactersRepository
import com.example.test.screens.characters.data.network.CharactersRepositoryImpl
import com.example.test.screens.characters.domain.usecase.GetCharactersUseCase
import com.example.test.screens.characters.domain.usecase.GetCharactersUseCaseImpl
import com.example.test.screens.characters.navigation.CharactersRouter
import com.example.test.screens.characters.navigation.CharactersRouterImpl
import dagger.Binds
import dagger.Module

@Module
interface CharactersBindModule {

    @Binds
    fun bindCharactersRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    fun bindGetCharactersUseCase(usecase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    fun bindCharactersRouter(router: CharactersRouterImpl): CharactersRouter
}