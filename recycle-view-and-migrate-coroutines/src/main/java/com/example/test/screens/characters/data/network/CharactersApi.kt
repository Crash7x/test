package com.example.test.screens.characters.data.network

import com.example.test.common.data.model.base.Response
import com.example.test.common.data.model.character.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApi {

    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>
}