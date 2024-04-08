package com.example.test.screens.characters.di

import com.example.test.screens.characters.data.network.CharactersApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CharactersProvideModule {

    @Provides
    fun provideCharactersApi(retrofit: Retrofit): CharactersApi = retrofit.create(CharactersApi::class.java)
}