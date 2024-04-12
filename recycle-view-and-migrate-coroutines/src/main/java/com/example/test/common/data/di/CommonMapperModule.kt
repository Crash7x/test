package com.example.test.common.data.di

import com.example.test.common.data.mapper.character.CharacterMapper
import com.example.test.common.data.mapper.character.CharacterLocationMapper
import com.example.test.common.data.mapper.character.OriginalMapper
import com.example.test.common.data.mapper.episode.EpisodeMapper
import com.example.test.common.data.mapper.location.LocationMapper
import com.example.test.common.data.model.character.CharacterResponse
import com.example.test.common.data.model.character.CharacterLocationResponse
import com.example.test.common.data.model.character.OriginalResponse
import com.example.test.common.data.model.episode.EpisodeResponse
import com.example.test.common.data.model.location.LocationResponse
import com.example.test.common.domain.model.character.Character
import com.example.test.common.domain.model.character.CharacterLocation
import com.example.test.common.domain.model.character.Original
import com.example.test.common.domain.model.episode.Episode
import com.example.test.common.domain.model.location.Location
import com.example.test.common.utils.Mapper
import dagger.Binds
import dagger.Module

@Module
interface CommonMapperModule {

    @Binds
    fun bindCharacterMapper(mapper: CharacterMapper): Mapper<CharacterResponse, Character>

    @Binds
    fun bindCharacterLocationMapper(mapper: CharacterLocationMapper): Mapper<CharacterLocationResponse, CharacterLocation>

    @Binds
    fun bindOriginalMapper(mapper: OriginalMapper): Mapper<OriginalResponse, Original>

    @Binds
    fun bindEpisodeMapper(mapper: EpisodeMapper): Mapper<EpisodeResponse, Episode>

    @Binds
    fun bindLocationMapper(mapper: LocationMapper): Mapper<LocationResponse, Location>

}