package com.example.test.common.data.di

import com.example.test.common.application.di.ApplicationScope
import com.example.test.common.data.network.character.CharactersApi
import com.example.test.common.data.network.episode.EpisodeApi
import com.example.test.common.data.network.location.LocationApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CommonApiModule {

    @ApplicationScope
    @Provides
    fun provideCharactersApi(retrofit: Retrofit): CharactersApi = retrofit.create(CharactersApi::class.java)

    @ApplicationScope
    @Provides
    fun provideEpisodeApi(retrofit: Retrofit): EpisodeApi = retrofit.create(EpisodeApi::class.java)

    @ApplicationScope
    @Provides
    fun provideLocationApi(retrofit: Retrofit): LocationApi = retrofit.create(LocationApi::class.java)
}