package com.example.test.screens.detail.character.adapter.header

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.recycle.view.and.migrate.coroutines.databinding.ItemCharacterLocationBinding
import com.example.recycle.view.and.migrate.coroutines.databinding.ItemHeaderCharacterBinding
import com.example.test.common.generic.list.adapter.BindingViewHolder

class HeaderViewHolder(
    private val binding: ItemHeaderCharacterBinding
) : BindingViewHolder<HeaderItem>(binding) {
    override fun bind(item: HeaderItem) {
        binding.header.text = binding.root.context.getString(item.header)
    }

    companion object {
        fun ViewHolder(viewGroup: ViewGroup) = HeaderViewHolder(
            ItemHeaderCharacterBinding.inflate(
                LayoutInflater.from(viewGroup.context), viewGroup, false
            )
        )
    }
}