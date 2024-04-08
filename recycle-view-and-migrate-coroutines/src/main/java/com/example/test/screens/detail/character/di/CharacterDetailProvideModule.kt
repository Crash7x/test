package com.example.test.screens.detail.character.di

import com.example.test.screens.detail.character.network.CharacterDetailApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CharacterDetailProvideModule {

    @Provides
    fun provideCharacterDetailApi(retrofit: Retrofit): CharacterDetailApi = retrofit.create(CharacterDetailApi::class.java)
}