package com.example.test.common.application.di

import android.app.Application
import com.example.test.common.application.TestApplication
import com.example.test.common.data.di.CommonApiModule
import com.example.test.common.data.di.CommonRepositoryModule
import com.example.test.common.data.di.CommonMapperModule
import com.example.test.common.data.di.NetworkModule
import com.example.test.common.data.model.character.CharacterResponse
import com.example.test.common.data.model.episode.EpisodeResponse
import com.example.test.common.data.model.location.LocationResponse
import com.example.test.common.data.network.character.CharactersRepository
import com.example.test.common.data.network.episode.EpisodeRepository
import com.example.test.common.data.network.location.LocationRepository
import com.example.test.common.domain.model.character.Character
import com.example.test.common.domain.model.episode.Episode
import com.example.test.common.domain.model.location.Location
import com.example.test.common.observable.di.ObservableModule
import com.example.test.common.observable.loader.LoaderFlow
import com.example.test.common.utils.Mapper
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ObservableModule::class,
        CommonMapperModule::class,
        CommonRepositoryModule::class,
        CommonApiModule::class
    ]
)
interface AppComponent {

    fun getRetrofit(): Retrofit
    fun getCharacterMapper(): Mapper<CharacterResponse, Character>
    fun getEpisodeMapper(): Mapper<EpisodeResponse, Episode>
    fun getLocationMapper(): Mapper<LocationResponse, Location>
    fun getCharactersRepository(): CharactersRepository
    fun getEpisodeRepository(): EpisodeRepository
    fun getLocationRepository(): LocationRepository
    fun getLoaderFlow(): LoaderFlow

    fun inject(app: TestApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}