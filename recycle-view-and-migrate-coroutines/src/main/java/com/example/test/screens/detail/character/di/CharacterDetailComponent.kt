package com.example.test.screens.detail.character.di

import com.example.test.common.application.di.AppComponent
import dagger.Component
import com.example.test.screens.characters.CharactersFragment
import com.example.test.screens.detail.character.CharactersDetailFragment

@CharacterDetailScope
@Component(
    modules = [
        CharacterDetailBindModule::class
    ],
    dependencies = [AppComponent::class]
)

interface CharacterDetailComponent {

    fun inject(fragment: CharactersDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): CharacterDetailComponent
    }
}