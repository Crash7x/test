package com.example.test.common.data.network.episode

import com.example.test.common.data.mapper.episode.EpisodeMapper
import com.example.test.common.data.model.base.getResultOrThrowException
import com.example.test.common.domain.model.episode.Episode
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val episodeApi: EpisodeApi,
    private val episodeMapper: EpisodeMapper
) : EpisodeRepository {
    override suspend fun getEpisode(): List<Episode> {
        val episode = episodeApi.getEpisode().getResultOrThrowException()
        return episode.map(episodeMapper::transform)
    }

    override suspend fun getEpisode(id: Int): Episode {
        val episode = episodeApi.getSingleEpisode(id)
        return episodeMapper.transform(episode)
    }

    override suspend fun getEpisodeIds(id: List<Int>): List<Episode> {
        val episode = episodeApi.getEpisodeIds(id)
        return episode.map(episodeMapper::transform)
    }


}