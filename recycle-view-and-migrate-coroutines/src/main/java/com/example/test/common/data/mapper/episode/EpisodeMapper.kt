package com.example.test.common.data.mapper.episode

import com.example.test.common.data.model.episode.EpisodeResponse
import com.example.test.common.domain.model.episode.Episode
import com.example.test.common.utils.Mapper
import javax.inject.Inject

class EpisodeMapper @Inject constructor() : Mapper<EpisodeResponse, Episode> {

    override fun transform(data: EpisodeResponse): Episode =
        Episode(
            id = data.id,
            name = data.name,
            airDate = data.airDate,
            episode = data.episode
        )
}