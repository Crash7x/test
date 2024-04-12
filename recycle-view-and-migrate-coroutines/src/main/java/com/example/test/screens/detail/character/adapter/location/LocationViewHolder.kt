package com.example.test.screens.detail.character.adapter.location

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.recycle.view.and.migrate.coroutines.databinding.ItemCharacterBinding
import com.example.recycle.view.and.migrate.coroutines.databinding.ItemCharacterLocationBinding
import com.example.recycle.view.and.migrate.coroutines.databinding.ItemEpisodeCharacterBinding
import com.example.test.common.generic.list.adapter.BindingViewHolder
import com.example.test.screens.characters.adapter.character.CharacterViewHolder

class LocationViewHolder(
    private val binding: ItemCharacterLocationBinding
) : BindingViewHolder<LocationItem>(binding) {
    override fun bind(item: LocationItem) {
        setupName(item)
        setupType(item)
        setupDimension(item)
    }

    private fun setupName(item: LocationItem) {
        binding.name.text = item.location.name
    }

    private fun setupType(item: LocationItem) {
        binding.type.text = item.location.type
    }

    private fun setupDimension(item: LocationItem) {
        binding.dimension.text = item.location.dimension
    }

    companion object {
        fun ViewHolder(viewGroup: ViewGroup) = LocationViewHolder(
            ItemCharacterLocationBinding.inflate(
                LayoutInflater.from(viewGroup.context), viewGroup, false
            )
        )
    }
}