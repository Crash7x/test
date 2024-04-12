package com.example.test.screens.detail.character.domain.mapper

import androidx.annotation.StringRes
import com.example.recycle.view.and.migrate.coroutines.R
import com.example.test.common.domain.model.character.Character
import com.example.test.common.generic.list.adapter.Item
import com.example.test.screens.detail.character.adapter.character.detail.CharacterDetailItem
import com.example.test.screens.detail.character.adapter.episode.EpisodeItem
import com.example.test.screens.detail.character.adapter.header.HeaderItem
import com.example.test.screens.detail.character.adapter.location.LocationItem
import com.example.test.screens.detail.character.domain.model.CharacterDetail

fun CharacterDetail.toItemList(
    onClickLocation: (Int) -> Unit,
    onClickEpisode: (Int) -> Unit
): List<Item> {
    val characterItems: List<Item> = listOf(CharacterDetailItem(this.character))
    val locationItems: List<Item> = location.map { LocationItem(it, onClickLocation) }
    val episodeItems: List<Item> = episode.map { EpisodeItem(it, onClickEpisode) }

   return characterItems
        .toMutableList()
        .apply {
            addAll(locationItems.addHeader(R.string.location))
            addAll(episodeItems.addHeader(R.string.episode))
        }
}

private fun List<Item>.addHeader(@StringRes id: Int) = toMutableList().apply {
    add(0, HeaderItem(id))
}