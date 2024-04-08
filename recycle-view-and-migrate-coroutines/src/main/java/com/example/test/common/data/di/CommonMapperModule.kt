package com.example.test.common.data.di

import com.example.test.common.data.mapper.CharacterMapper
import com.example.test.common.data.mapper.LocationMapper
import com.example.test.common.data.mapper.OriginalMapper
import com.example.test.common.data.model.character.CharacterResponse
import com.example.test.common.data.model.character.LocationResponse
import com.example.test.common.data.model.character.OriginalResponse
import com.example.test.common.domain.model.Character
import com.example.test.common.domain.model.Location
import com.example.test.common.domain.model.Original
import com.example.test.common.utils.Mapper
import dagger.Binds
import dagger.Module

@Module
interface CommonMapperModule {

    @Binds
    fun bindCharacterMapper(mapper: CharacterMapper): Mapper<CharacterResponse, Character>

    @Binds
    fun bindLocationMapper(mapper: LocationMapper): Mapper<LocationResponse, Location>

    @Binds
    fun bindOriginalMapper(mapper: OriginalMapper): Mapper<OriginalResponse, Original>
}