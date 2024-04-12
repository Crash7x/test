package com.example.test.screens.detail.character.adapter.header

import androidx.annotation.StringRes
import com.example.test.common.generic.list.adapter.Item
import com.example.test.screens.detail.character.adapter.ItemType

class HeaderItem(
    @StringRes
    val header: Int
) : Item() {
    override val identifier: Long = header.hashCode().toLong()
    override val type: Int = ItemType.HEADER.value
}