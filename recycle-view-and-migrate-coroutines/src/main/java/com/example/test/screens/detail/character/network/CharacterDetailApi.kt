package com.example.test.screens.detail.character.network

import com.example.test.common.data.model.character.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterDetailApi {

    @GET("character/{id}")
    suspend fun getCharacterDetail(@Path("id") id: Int): CharacterResponse
}