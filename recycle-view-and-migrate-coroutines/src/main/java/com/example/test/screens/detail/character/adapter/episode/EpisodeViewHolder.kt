package com.example.test.screens.detail.character.adapter.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.example.recycle.view.and.migrate.coroutines.R
import com.example.recycle.view.and.migrate.coroutines.databinding.ItemCharacterBinding
import com.example.recycle.view.and.migrate.coroutines.databinding.ItemCharacterDetailBinding
import com.example.recycle.view.and.migrate.coroutines.databinding.ItemEpisodeCharacterBinding
import com.example.test.common.generic.list.adapter.BindingViewHolder
import com.example.test.screens.characters.adapter.character.CharacterItem
import com.example.test.screens.characters.adapter.character.CharacterViewHolder

class EpisodeViewHolder(
    private val binding: ItemEpisodeCharacterBinding
) : BindingViewHolder<EpisodeItem>(binding) {
    override fun bind(item: EpisodeItem) {
        setupEpisode(item)
        setupName(item)
        setupAirDate(item)
    }

    private fun setupEpisode(item: EpisodeItem) {
        binding.episode.text = item.episode.episode
    }

    private fun setupName(item: EpisodeItem) {
        binding.name.text = item.episode.name
    }

    private fun setupAirDate(item: EpisodeItem) {
        binding.airDate.text = item.episode.airDate
    }

    companion object {
        fun ViewHolder(viewGroup: ViewGroup) = EpisodeViewHolder(
            ItemEpisodeCharacterBinding.inflate(
                LayoutInflater.from(viewGroup.context), viewGroup, false
            )
        )
    }
}