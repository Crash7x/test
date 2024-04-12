package com.example.test.common.data.network.episode

import com.example.test.common.domain.model.episode.Episode

interface EpisodeRepository {
    suspend fun getEpisode(): List<Episode>

    suspend fun getEpisode(id: Int): Episode

    suspend fun getEpisodeIds(id: List<Int>): List<Episode>
}