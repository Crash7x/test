package com.example.test.common.generic.list.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.common.generic.list.adapter.BindingViewHolder

internal fun RecyclerView.LayoutManager?.saveScrollState(
    key: Int,
    viewHolder: BindingViewHolder<*>,
    listener: (key: Int, position: Int, firstVisiblePosition: Int) -> Unit
) {
    if (this is LinearLayoutManager) {
        listener(key, viewHolder.adapterPosition, findFirstVisibleItemPosition())
    }
}

internal fun RecyclerView.LayoutManager?.restoreScrollState(
    key: Int,
    viewHolder: BindingViewHolder<*>,
    listener: (key: Int, position: Int) -> Int
) {
    val lastPosition = listener(key, viewHolder.adapterPosition)
    if (lastPosition >= 0) {
        this?.scrollToPosition(lastPosition)
    }
}