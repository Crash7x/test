package com.example.test.screens.characters.domain.mapper

import com.example.test.common.domain.model.Character
import com.example.test.screens.characters.adapter.character.CharacterItem

fun List<Character>.toItemList(
    onClickCharacter: (Int) -> Unit,
) = mapNotNull {
    CharacterItem(it, onClickCharacter)
}