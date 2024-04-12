package com.example.test.screens.detail.character.adapter.location

import com.example.test.common.domain.model.location.Location
import com.example.test.common.generic.list.adapter.Item
import com.example.test.screens.detail.character.adapter.ItemType

class LocationItem(
    val location: Location,
    val onClick: (Int) -> Unit
) : Item() {
    override val identifier: Long = location.id.toLong()
    override val type: Int = ItemType.LOCATION.value
}