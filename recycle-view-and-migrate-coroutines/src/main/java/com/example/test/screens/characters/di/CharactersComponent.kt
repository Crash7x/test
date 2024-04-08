package com.example.test.screens.characters.di

import com.example.test.common.application.di.AppComponent
import dagger.Component
import com.example.test.screens.characters.CharactersFragment

@CharactersScope
@Component(
    modules = [
        CharactersBindModule::class,
        CharactersProvideModule::class
    ],
    dependencies = [AppComponent::class]
)

interface CharactersComponent {

    fun inject(fragment: CharactersFragment)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): CharactersComponent
    }
}