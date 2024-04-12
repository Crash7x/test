package com.example.test.screens.detail.character.adapter.episode

import com.example.test.common.domain.model.episode.Episode
import com.example.test.common.generic.list.adapter.Item
import com.example.test.screens.detail.character.adapter.ItemType

class EpisodeItem(
    val episode: Episode,
    val onClick: (Int) -> Unit
) : Item() {
    override val identifier: Long = episode.id.toLong()
    override val type: Int = ItemType.EPISODE.value
}