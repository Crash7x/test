package com.example.test.screens.characters.navigation

import com.example.test.common.navigation.NavCommand
import com.example.test.common.navigation.toNavCommand
import com.example.test.screens.characters.CharactersFragmentDirections
import javax.inject.Inject

class CharactersRouterImpl @Inject constructor() : CharactersRouter {
    override fun getCharacterDetail(characterId: Int): NavCommand {
        return CharactersFragmentDirections.actionCharactersFragmentToCharactersDetailFragment(characterId).toNavCommand()
    }

}