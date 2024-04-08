package com.example.test.screens.characters.adapter.character

import com.example.test.common.domain.model.Character
import com.example.test.common.generic.list.adapter.Item
import com.example.test.screens.characters.adapter.ItemType

class CharacterItem(
    val character: Character,
    val onClick: (Int) -> Unit
) : Item() {
    override val identifier: Long = character.id.toLong()
    override val type: Int = ItemType.CHARACTER.value
}