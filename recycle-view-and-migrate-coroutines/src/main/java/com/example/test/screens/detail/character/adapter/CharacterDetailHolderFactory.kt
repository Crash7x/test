package com.example.test.screens.detail.character.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.common.generic.list.adapter.GenericListAdapter
import com.example.test.screens.detail.character.adapter.character.detail.CharacterDetailViewHolder
import com.example.test.screens.detail.character.adapter.episode.EpisodeViewHolder
import com.example.test.screens.detail.character.adapter.header.HeaderViewHolder
import com.example.test.screens.detail.character.adapter.location.LocationViewHolder
import javax.inject.Inject

class CharacterDetailHolderFactory @Inject constructor() : GenericListAdapter.ViewHolderFactory {

    override fun create(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.CHARACTER_DETAIL.value -> CharacterDetailViewHolder.ViewHolder(parent)
            ItemType.LOCATION.value -> LocationViewHolder.ViewHolder(parent)
            ItemType.EPISODE.value -> EpisodeViewHolder.ViewHolder(parent)
            ItemType.HEADER.value -> HeaderViewHolder.ViewHolder(parent)
            else -> throw IllegalStateException("Unidentified type View Holder")
        }
    }
}