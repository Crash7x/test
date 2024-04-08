package com.example.test.screens.characters.adapter.character

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.example.recycle.view.and.migrate.coroutines.R
import com.example.recycle.view.and.migrate.coroutines.databinding.ItemCharacterBinding
import com.example.test.common.generic.list.adapter.BindingViewHolder

class CharacterViewHolder(
    private val binding: ItemCharacterBinding
) : BindingViewHolder<CharacterItem>(binding) {
    override fun bind(item: CharacterItem) {
        setupName(item)
        setupStatusAndSpecies(item)
        setupLocation(item)
        setupGender(item)
        setupImage(item)
        setupListener(item)
    }

    private fun setupName(item: CharacterItem) {
        binding.name.text = item.character.name
    }

    private fun setupStatusAndSpecies(item: CharacterItem) {
        binding.statusSpecies.text = binding.root.context.getString(
            R.string.status_species,
            item.character.status.value,
            item.character.species
        )
    }

    private fun setupLocation(item: CharacterItem) {
        binding.location.text = item.character.location.name
    }

    private fun setupGender(item: CharacterItem) {
        binding.gender.text = item.character.gender.value
    }

    private fun setupImage(item: CharacterItem) {
        binding.image.load(item.character.image)
    }

    private fun setupListener(item: CharacterItem) {
        binding.root.setOnClickListener {
            item.onClick(item.character.id)
        }
    }

    companion object {
        fun ViewHolder(viewGroup: ViewGroup) = CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(viewGroup.context), viewGroup, false
            )
        )
    }
}