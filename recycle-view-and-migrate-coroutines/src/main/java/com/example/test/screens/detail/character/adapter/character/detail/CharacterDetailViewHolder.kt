package com.example.test.screens.detail.character.adapter.character.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.example.recycle.view.and.migrate.coroutines.R
import com.example.recycle.view.and.migrate.coroutines.databinding.ItemCharacterBinding
import com.example.recycle.view.and.migrate.coroutines.databinding.ItemCharacterDetailBinding
import com.example.test.common.generic.list.adapter.BindingViewHolder
import com.example.test.screens.characters.adapter.character.CharacterItem
import com.example.test.screens.characters.adapter.character.CharacterViewHolder

class CharacterDetailViewHolder(
    private val binding: ItemCharacterDetailBinding
) : BindingViewHolder<CharacterDetailItem>(binding) {
    override fun bind(item: CharacterDetailItem) {
        setupName(item)
        setupStatusAndSpecies(item)
        setupOrigin(item)
        setupLocation(item)
        setupGender(item)
        setupImage(item)
    }

    private fun setupName(item: CharacterDetailItem) {
        binding.name.text = item.character.name
    }

    private fun setupImage(item: CharacterDetailItem) {
        binding.image.load(item.character.image)
    }

    private fun setupStatusAndSpecies(item: CharacterDetailItem) {
        binding.statusSpecies.text = binding.root.context.getString(
            R.string.status_species,
            item.character.status.value,
            item.character.species
        )
    }

    private fun setupGender(item: CharacterDetailItem) {
        binding.gender.text = item.character.gender.value
    }

    private fun setupOrigin(item: CharacterDetailItem) {
        binding.type.text = item.character.type
    }

    private fun setupLocation(item: CharacterDetailItem) {
        binding.origin.text = item.character.origin.name
    }

    companion object {
        fun ViewHolder(viewGroup: ViewGroup) = CharacterDetailViewHolder(
            ItemCharacterDetailBinding.inflate(
                LayoutInflater.from(viewGroup.context), viewGroup, false
            )
        )
    }
}