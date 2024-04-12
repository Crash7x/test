package com.example.test.common.data.network.character

import com.example.test.common.data.model.base.Response
import com.example.test.common.data.model.character.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApi {

    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getSingleCharacters(@Path("id") id: Int): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharactersIds(@Path("id") id: List<Int>): List<CharacterResponse>
}