package com.example.test.common.data.di

import com.example.test.common.data.network.character.CharactersRepository
import com.example.test.common.data.network.character.CharactersRepositoryImpl
import com.example.test.common.data.network.episode.EpisodeRepository
import com.example.test.common.data.network.episode.EpisodeRepositoryImpl
import com.example.test.common.data.network.location.LocationRepository
import com.example.test.common.data.network.location.LocationRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface CommonRepositoryModule {

    @Binds
    fun bindCharactersRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    fun bindLocationRepository(repository: LocationRepositoryImpl): LocationRepository

    @Binds
    fun bindEpisodeRepository(repository: EpisodeRepositoryImpl): EpisodeRepository

}