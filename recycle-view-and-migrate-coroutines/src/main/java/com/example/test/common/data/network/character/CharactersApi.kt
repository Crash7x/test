package com.example.test.common.data.network.character

import com.example.test.common.data.model.base.Response
import com.example.test.common.data.model.character.CharacterResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApi {

    @GET("character")
    fun getCharacters(): Single<Response<CharacterResponse>>

    @GET("character/{id}")
    fun getSingleCharacters(@Path("id") id: Int): Single<CharacterResponse>

    @GET("character/{id}")
    fun getCharactersIds(@Path("id") id: List<Int>): Single<List<CharacterResponse>>
}