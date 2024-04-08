package com.example.test.screens.characters.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.common.generic.list.adapter.GenericListAdapter
import com.example.test.screens.characters.adapter.character.CharacterViewHolder
import javax.inject.Inject

class CharacterHolderFactory @Inject constructor() : GenericListAdapter.ViewHolderFactory {

    override fun create(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.CHARACTER.value -> CharacterViewHolder.ViewHolder(parent)
            else -> throw IllegalStateException("Unidentified type View Holder")
        }
    }
}