package com.example.test.common.application.di

import android.app.Application
import com.example.test.common.application.TestApplication
import com.example.test.common.data.di.CommonMapperModule
import com.example.test.common.data.di.NetworkModule
import com.example.test.common.data.mapper.CharacterMapper
import com.example.test.common.data.mapper.LocationMapper
import com.example.test.common.data.mapper.OriginalMapper
import com.example.test.common.data.model.character.CharacterResponse
import com.example.test.common.data.model.character.LocationResponse
import com.example.test.common.data.model.character.OriginalResponse
import com.example.test.common.domain.model.Character
import com.example.test.common.domain.model.Location
import com.example.test.common.domain.model.Original
import com.example.test.common.observable.di.ObservableModule
import com.example.test.common.observable.loader.LoaderFlow
import com.example.test.common.utils.Mapper
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ObservableModule::class,
        CommonMapperModule::class
    ]
)
interface AppComponent {

    fun getRetrofit(): Retrofit
    fun getCharacterMapper(): Mapper<CharacterResponse, Character>
    fun getLoaderFlow(): LoaderFlow

    fun inject(app: TestApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}