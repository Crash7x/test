package com.example.test.common.generic.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class DefaultViewHolder<T: Item>(viewGroup: ViewGroup) : BaseViewHolder<T>(viewGroup) {
    /**
     * Binds the given item's data to this view holder.
     */
    abstract override fun bind(item: T)

    /**
     * Partially binds the given item's data to this view holder.
     *
     * For detailed information refer to [RecyclerView.Adapter.onBindViewHolder].
     */
    override fun bind(item: T, payloads: List<Any>) = Unit

    /**
     * Releases resources when its recycled.
     */
    override fun unbind() = Unit
}