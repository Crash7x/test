package com.example.test.screens.characters.navigation

import com.example.test.common.navigation.NavCommand


interface CharactersRouter {

    fun getCharacterDetail(characterId: Int): NavCommand
}