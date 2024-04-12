package com.example.test.common.data.network.episode

import com.example.test.common.data.model.base.Response
import com.example.test.common.data.model.character.CharacterResponse
import com.example.test.common.data.model.episode.EpisodeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {

    @GET("episode")
    suspend fun getEpisode(): Response<EpisodeResponse>

    @GET("episode/{id}")
    suspend fun getSingleEpisode(@Path("id") id: Int): EpisodeResponse

    @GET("episode/{id}")
    suspend fun getEpisodeIds(@Path("id") id: List<Int>): List<EpisodeResponse>
}